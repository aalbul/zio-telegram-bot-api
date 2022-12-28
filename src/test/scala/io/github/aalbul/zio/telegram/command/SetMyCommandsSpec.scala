package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.SetMyCommands.SetMyCommandsPayload
import io.github.aalbul.zio.telegram.domain.BotCommandScopeAllPrivateChats
import io.github.aalbul.zio.telegram.test.BaseSpec

class SetMyCommandsSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = SetMyCommands
      .of(Seq(botCommand1, botCommand2))
      .withScope(BotCommandScopeAllPrivateChats.of)
      .withLanguageCode("EN")
    val payload: SetMyCommandsPayload = SetMyCommandsPayload(
      commands = Seq(botCommand1, botCommand2),
      scope = Some(BotCommandScopeAllPrivateChats.of),
      languageCode = Some("EN")
    )
  }

  "SetMyCommands" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "setMyCommands"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "SetMyCommandsPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/set-my-commands-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[SetMyCommandsPayload]("json/command/set-my-commands-payload.json") shouldBe payload
        }
      }
    }
  }
}
