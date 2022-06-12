package com.github.aalbul.zio.telegram.command

import com.github.aalbul.zio.telegram.command.FileDescriptor.{idDescriptor, pathDescriptor}
import com.github.aalbul.zio.telegram.command.MultipartBody.{filePart, stringPart}
import com.github.aalbul.zio.telegram.domain.{Message, ParseModes}
import com.github.aalbul.zio.telegram.test.BaseSpec

class SendDocumentSpec extends BaseSpec {
  trait Scope {
    val command: Command[Message] =
      SendDocument
        .of(chatId = "5521", document = idDescriptor("980003"))
        .withThumb(pathDescriptor("/tmp/5.png"))
        .withCaption("pdf document")
        .withParseMode(ParseModes.MarkdownV2)
        .withCaptionEntities(Seq(messageEntity1))
        .withDisableContentTypeDetection(true)
        .withDisableNotification(true)
        .withProtectContent(false)
        .withReplyToMessageId(50)
        .withAllowSendingWithoutReply(true)
        .withReplyMarkup(forceReplyMarkup1)
  }

  "SendDocument" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "sendDocument"
      }
    }

    "parameters" should {
      "represent parameters as form data" in new Scope {
        command.parameters shouldBe MultipartBody.of(
          stringPart("chat_id", "5521"),
          stringPart("document", "980003"),
          filePart("thumb", "/tmp/5.png"),
          stringPart("caption", "pdf document"),
          stringPart("parse_mode", "MarkdownV2"),
          stringPart("caption_entities", jsonResourceString("json/command/caption-entities.json")),
          stringPart("disable_content_type_detection", "true"),
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
