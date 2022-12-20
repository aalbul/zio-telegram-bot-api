package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.FileDescriptor.{pathDescriptor, urlDescriptor}
import io.github.aalbul.zio.telegram.command.MultipartBody.{filePart, stringPart}
import io.github.aalbul.zio.telegram.domain.{Message, ParseMode}
import io.github.aalbul.zio.telegram.test.BaseSpec

class SendAnimationSpec extends BaseSpec {
  trait Scope {
    val command: Command[Message] =
      SendAnimation
        .of(chatId = "5521", animation = urlDescriptor("https://google.com/image.gif"))
        .withMessageThreadId(25)
        .withDuration(2000)
        .withWidth(1024)
        .withHeight(768)
        .withThumb(pathDescriptor("/tmp/thumb.jpg"))
        .withCaption("funny image")
        .withParseMode(ParseMode.MarkdownV2)
        .withCaptionEntities(Seq(messageEntity1))
        .withDisableNotification(true)
        .withProtectContent(false)
        .withReplyToMessageId(50)
        .withAllowSendingWithoutReply(true)
        .withReplyMarkup(forceReplyMarkup1)
  }

  "SendAnimation" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "sendAnimation"
      }
    }

    "parameters" should {
      "represent parameters as form data" in new Scope {
        command.parameters shouldBe MultipartBody.of(
          stringPart("chat_id", "5521"),
          stringPart("message_thread_id", "25"),
          stringPart("animation", "https://google.com/image.gif"),
          stringPart("duration", "2000"),
          stringPart("width", "1024"),
          stringPart("height", "768"),
          filePart("thumb", "/tmp/thumb.jpg"),
          stringPart("caption", "funny image"),
          stringPart("parse_mode", "MarkdownV2"),
          stringPart("caption_entities", jsonResourceString("json/command/caption-entities.json")),
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
