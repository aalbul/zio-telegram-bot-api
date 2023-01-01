package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.FileDescriptor.{idDescriptor, pathDescriptor}
import io.github.aalbul.zio.telegram.command.MultipartBody.{filePart, stringPart}
import io.github.aalbul.zio.telegram.domain.{Message, ParseMode}
import io.github.aalbul.zio.telegram.test.BaseSpec

import scala.concurrent.duration.DurationInt

class SendVideoSpec extends BaseSpec {
  trait Scope {
    val command: Command[Message] =
      SendVideo
        .of(chatId = "802", video = idDescriptor("980007"))
        .withMessageThreadId(35)
        .withDuration(3500.seconds)
        .withWidth(1920)
        .withHeight(1080)
        .withThumb(pathDescriptor("/tmp/10.png"))
        .withCaption("my video")
        .withParseMode(ParseMode.MarkdownV2)
        .withCaptionEntities(Seq(messageEntity1))
        .withHasSpoiler(true)
        .withSupportsStreaming(true)
        .withDisableNotification(true)
        .withProtectContent(false)
        .withReplyToMessageId(50)
        .withAllowSendingWithoutReply(true)
        .withReplyMarkup(forceReplyMarkup1)
  }

  "SendVideo" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "sendVideo"
      }
    }

    "parameters" should {
      "represent parameters as form data" in new Scope {
        command.parameters shouldBe MultipartBody.of(
          stringPart("chat_id", "802"),
          stringPart("message_thread_id", "35"),
          stringPart("video", "980007"),
          stringPart("duration", "3500"),
          stringPart("width", "1920"),
          stringPart("height", "1080"),
          filePart("thumb", "/tmp/10.png"),
          stringPart("caption", "my video"),
          stringPart("parse_mode", "MarkdownV2"),
          stringPart("caption_entities", jsonResourceString("json/command/caption-entities.json")),
          stringPart("has_spoiler", "true"),
          stringPart("disable_notification", "true"),
          stringPart("protect_content", "false"),
          stringPart("reply_to_message_id", "50"),
          stringPart("allow_sending_without_reply", "true"),
          stringPart("reply_markup", jsonResourceString("json/command/reply-markup.json"))
        )
      }
    }
  }
}
