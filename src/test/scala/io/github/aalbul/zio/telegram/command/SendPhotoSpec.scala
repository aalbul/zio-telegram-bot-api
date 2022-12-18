package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.FileDescriptor.idDescriptor
import io.github.aalbul.zio.telegram.command.MultipartBody.{filePart, stringPart}
import io.github.aalbul.zio.telegram.domain.{Message, ParseMode}
import io.github.aalbul.zio.telegram.test.BaseSpec

class SendPhotoSpec extends BaseSpec {
  trait Scope {
    val command: Command[Message] =
      SendPhoto
        .of(chatId = "801", photo = idDescriptor("980006"))
        .withCaption("my photo")
        .withParseMode(ParseMode.HTML)
        .withCaptionEntities(Seq(messageEntity1))
        .withDisableNotification(true)
        .withProtectContent(false)
        .withReplyToMessageId(50)
        .withAllowSendingWithoutReply(true)
        .withReplyMarkup(forceReplyMarkup1)
  }

  "SendPhoto" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "sendPhoto"
      }
    }

    "parameters" should {
      "represent parameters as form data" in new Scope {
        command.parameters shouldBe MultipartBody.of(
          stringPart("chat_id", "801"),
          stringPart("photo", "980006"),
          stringPart("caption", "my photo"),
          stringPart("parse_mode", "HTML"),
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
