package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.FileDescriptor.{idDescriptor, pathDescriptor}
import io.github.aalbul.zio.telegram.command.MultipartBody.{filePart, stringPart}
import io.github.aalbul.zio.telegram.domain.Message
import io.github.aalbul.zio.telegram.test.BaseSpec

import scala.concurrent.duration.DurationInt

class SendVideoNoteSpec extends BaseSpec {
  trait Scope {
    val command: Command[Message] =
      SendVideoNote
        .of(chatId = "805", videoNote = idDescriptor("980009"))
        .withMessageThreadId(36)
        .withDuration(670.seconds)
        .withLength(5600)
        .withThumb(pathDescriptor("/tmp/11.png"))
        .withDisableNotification(true)
        .withProtectContent(false)
        .withReplyToMessageId(50)
        .withAllowSendingWithoutReply(true)
        .withReplyMarkup(forceReplyMarkup1)
  }

  "SendVideoNote" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "sendVideoNote"
      }
    }

    "parameters" should {
      "represent parameters as form data" in new Scope {
        command.parameters shouldBe MultipartBody.of(
          stringPart("chat_id", "805"),
          stringPart("message_thread_id", "36"),
          stringPart("video_note", "980009"),
          stringPart("duration", "670"),
          stringPart("length", "5600"),
          filePart("thumb", "/tmp/11.png"),
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
