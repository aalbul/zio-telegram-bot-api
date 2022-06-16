package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.FileDescriptor.idDescriptor
import io.github.aalbul.zio.telegram.command.MultipartBody.{filePart, stringPart}
import io.github.aalbul.zio.telegram.domain.{Message, ParseModes}
import io.github.aalbul.zio.telegram.test.BaseSpec

class SendVoiceSpec extends BaseSpec {
  trait Scope {
    val command: Command[Message] =
      SendVoice
        .of(chatId = "803", voice = idDescriptor("980008"))
        .withCaption("my voice")
        .withParseMode(ParseModes.MarkdownV2)
        .withCaptionEntities(Seq(messageEntity1))
        .withDuration(400)
        .withDisableNotification(true)
        .withProtectContent(false)
        .withReplyToMessageId(50)
        .withAllowSendingWithoutReply(true)
        .withReplyMarkup(forceReplyMarkup1)
  }

  "SendVoice" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "sendVoice"
      }
    }

    "parameters" should {
      "represent parameters as form data" in new Scope {
        command.parameters shouldBe MultipartBody.of(
          stringPart("chat_id", "803"),
          stringPart("voice", "980008"),
          stringPart("caption", "my voice"),
          stringPart("parse_mode", "MarkdownV2"),
          stringPart("caption_entities", jsonResourceString("json/command/caption-entities.json")),
          stringPart("duration", "400"),
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
