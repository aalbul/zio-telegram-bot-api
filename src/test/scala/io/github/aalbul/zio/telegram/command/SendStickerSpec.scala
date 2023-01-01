package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.FileDescriptor.idDescriptor
import io.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import io.github.aalbul.zio.telegram.domain.Message
import io.github.aalbul.zio.telegram.test.BaseSpec

class SendStickerSpec extends BaseSpec {
  trait Scope {
    val command: Command[Message] =
      SendSticker
        .of(chatId = "5525", sticker = idDescriptor("sticker-123"))
        .withMessageThreadId(28)
        .withDisableNotification(true)
        .withProtectContent(false)
        .withReplyToMessageId(55)
        .withAllowSendingWithoutReply(true)
        .withReplyMarkup(forceReplyMarkup1)
  }

  "SendSticker" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "sendSticker"
      }
    }

    "parameters" should {
      "represent parameters as form data" in new Scope {
        command.parameters shouldBe MultipartBody.of(
          stringPart("chat_id", "5525"),
          stringPart("message_thread_id", "28"),
          stringPart("sticker", "sticker-123"),
          stringPart("disable_notification", "true"),
          stringPart("protect_content", "false"),
          stringPart("reply_to_message_id", "55"),
          stringPart("allow_sending_without_reply", "true"),
          stringPart("reply_markup", jsonResourceString("json/command/reply-markup.json"))
        )
      }
    }
  }
}
