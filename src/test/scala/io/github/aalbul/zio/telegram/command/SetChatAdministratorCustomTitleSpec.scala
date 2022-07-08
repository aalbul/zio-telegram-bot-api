package io.github.aalbul.zio.telegram.command

import io.circe.syntax.EncoderOps
import io.github.aalbul.zio.telegram.command.SetChatAdministratorCustomTitle.SetChatAdministratorCustomTitlePayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class SetChatAdministratorCustomTitleSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] =
      SetChatAdministratorCustomTitle.of(chatId = "126", userId = "usr-5", customTitle = "test title")

    val payload: SetChatAdministratorCustomTitlePayload = SetChatAdministratorCustomTitlePayload(
      chatId = "126",
      userId = "usr-5",
      customTitle = "test title"
    )
  }

  "SetChatAdministratorCustomTitle" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "setChatAdministratorCustomTitle"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "SetChatAdministratorCustomTitlePayload" should {
      "serialize payload to json" in new Scope {
        payload.asJson shouldBe jsonResource("json/command/set-chat-administrator-custom-title-payload.json")
      }
    }
  }
}
