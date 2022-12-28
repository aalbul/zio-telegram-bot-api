package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.GetMyCommands.GetMyCommandsPayload
import io.github.aalbul.zio.telegram.domain.{BotCommand, BotCommandScopeAllPrivateChats}
import io.github.aalbul.zio.telegram.test.BaseSpec

class GetMyCommandsSpec extends BaseSpec {
  trait Scope {
    val command: Command[Seq[BotCommand]] = GetMyCommands
      .of
      .withScope(BotCommandScopeAllPrivateChats.of)
      .withLanguageCode("EN")
    val payload: GetMyCommandsPayload = GetMyCommandsPayload(
      scope = Some(BotCommandScopeAllPrivateChats.of),
      languageCode = Some("EN")
    )
  }

  "GetMyCommands" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "getMyCommands"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "GetMyCommandsPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/get-my-commands-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[GetMyCommandsPayload]("json/command/get-my-commands-payload.json") shouldBe payload
        }
      }
    }
  }
}
