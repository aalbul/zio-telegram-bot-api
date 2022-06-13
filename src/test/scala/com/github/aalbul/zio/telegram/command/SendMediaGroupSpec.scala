package com.github.aalbul.zio.telegram.command

import com.github.aalbul.zio.telegram.command.MultipartBody.{filePart, stringPart}
import com.github.aalbul.zio.telegram.domain.Message
import com.github.aalbul.zio.telegram.test.BaseSpec

class SendMediaGroupSpec extends BaseSpec {
  trait Scope {
    val command: Command[Seq[Message]] =
      SendMediaGroup
        .of(
          chatId = "5526",
          media = Seq(
            inputMediaAnimation,
            inputMediaAudio,
            inputMediaDocument,
            inputMediaPhoto,
            inputMediaVideo
          )
        )
        .withDisableNotification(true)
        .withProtectContent(false)
        .withReplyToMessageId(50)
        .withAllowSendingWithoutReply(true)
  }

  "SendMediaGroup" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "sendMediaGroup"
      }
    }

    "parameters" should {
      "represent parameters as form data" in new Scope {
        command.parameters shouldBe MultipartBody.of(
          stringPart("chat_id", "5526"),
          stringPart("media", jsonResourceString("json/command/input-medias.json")),
          stringPart("disable_notification", "true"),
          stringPart("protect_content", "false"),
          stringPart("reply_to_message_id", "50"),
          stringPart("allow_sending_without_reply", "true"),
          filePart("/tmp/one.gif", "/tmp/one.gif"),
          filePart("/tmp/one.mp3", "/tmp/one.mp3"),
          filePart("/tmp/song_thumb.jpg", "/tmp/song_thumb.jpg"),
          filePart("/tmp/one.pdf", "/tmp/one.pdf"),
          filePart("/tmp/document_thumb.jpg", "/tmp/document_thumb.jpg"),
          filePart("/tmp/one.jpg", "/tmp/one.jpg"),
          filePart("/tmp/photo_thumb.jpg", "/tmp/photo_thumb.jpg"),
          filePart("/tmp/one.mp4", "/tmp/one.mp4"),
          filePart("/tmp/video_thumb.jpg", "/tmp/video_thumb.jpg")
        )
      }
    }
  }
}
