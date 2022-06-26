package io.github.aalbul.zio.telegram.command

import io.circe.syntax.EncoderOps
import io.github.aalbul.zio.telegram.command.SetChatPermissions.SetChatPermissionsPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class SetChatPermissionsSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = SetChatPermissions.of(chatId = "2256", permissions = chatPermissions1)

    val payload: SetChatPermissionsPayload = SetChatPermissionsPayload(chatId = "2256", permissions = chatPermissions1)
  }

  "SetChatPermissions" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "setChatPermissions"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "SetChatPermissionsPayload" should {
      "serialize payload to json" in new Scope {
        payload.asJson shouldBe jsonResource("json/command/set-chat-permissions-payload.json")
      }
    }
  }
}
