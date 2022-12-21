package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import io.github.aalbul.zio.telegram.test.BaseSpec

class DeleteChatPhotoSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = DeleteChatPhoto.of(chatId = "9931")
  }

  "DeleteChatPhoto" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "deleteChatPhoto"
      }
    }

    "parameters" should {
      "represent parameters as form data" in new Scope {
        command.parameters shouldBe MultipartBody.of(
          stringPart("chat_id", "9931")
        )
      }
    }
  }
}
