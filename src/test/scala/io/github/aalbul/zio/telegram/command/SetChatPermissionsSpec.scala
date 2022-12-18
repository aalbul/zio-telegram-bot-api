package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
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
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource(
            "json/command/set-chat-permissions-payload.json"
          )
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[SetChatPermissionsPayload](
            "json/command/set-chat-permissions-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
