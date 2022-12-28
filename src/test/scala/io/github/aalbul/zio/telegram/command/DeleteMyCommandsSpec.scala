package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.DeleteMyCommands.DeleteMyCommandsPayload
import io.github.aalbul.zio.telegram.domain.BotCommandScopeAllPrivateChats
import io.github.aalbul.zio.telegram.test.BaseSpec

class DeleteMyCommandsSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = DeleteMyCommands
      .of()
      .withScope(BotCommandScopeAllPrivateChats.of())
      .withLanguageCode("EN")
    val payload: DeleteMyCommandsPayload = DeleteMyCommandsPayload(
      scope = Some(BotCommandScopeAllPrivateChats.of()),
      languageCode = Some("EN")
    )
  }

  "DeleteMyCommands" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "deleteMyCommands"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "DeleteMyCommandsPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/delete-my-commands-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[DeleteMyCommandsPayload](
            "json/command/delete-my-commands-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
