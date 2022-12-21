package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.FileDescriptor.idDescriptor
import io.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import io.github.aalbul.zio.telegram.test.BaseSpec

class SetChatPhotoSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] =
      SetChatPhoto
        .of(chatId = "9930", photo = idDescriptor("980005"))
  }

  "SetChatPhoto" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "setChatPhoto"
      }
    }

    "parameters" should {
      "represent parameters as form data" in new Scope {
        command.parameters shouldBe MultipartBody.of(
          stringPart("chat_id", "9930"),
          stringPart("photo", "980005")
        )
      }
    }
  }
}
