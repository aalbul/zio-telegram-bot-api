package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import io.github.aalbul.zio.telegram.test.BaseSpec

class SetChatTitleSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = SetChatTitle.of(chatId = "9932", title = "new title")
  }

  "SetChatTitle" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "setChatTitle"
      }
    }

    "parameters" should {
      "represent parameters as form data" in new Scope {
        command.parameters shouldBe MultipartBody.of(
          stringPart("chat_id", "9932"),
          stringPart("title", "new title")
        )
      }
    }
  }
}
