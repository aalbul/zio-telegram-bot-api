package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.SetChatAdministratorCustomTitle.SetChatAdministratorCustomTitlePayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class SetChatAdministratorCustomTitleSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] =
      SetChatAdministratorCustomTitle.of(chatId = "126", userId = 55, customTitle = "test title")

    val payload: SetChatAdministratorCustomTitlePayload = SetChatAdministratorCustomTitlePayload(
      chatId = "126",
      userId = 55,
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
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource(
            "json/command/set-chat-administrator-custom-title-payload.json"
          )
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[SetChatAdministratorCustomTitlePayload](
            "json/command/set-chat-administrator-custom-title-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
