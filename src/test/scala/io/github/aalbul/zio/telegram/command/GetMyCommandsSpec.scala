package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import io.github.aalbul.zio.telegram.domain.{BotCommand, BotCommandScopeAllPrivateChats}
import io.github.aalbul.zio.telegram.test.BaseSpec

class GetMyCommandsSpec extends BaseSpec {
  trait Scope {
    val command: Command[Seq[BotCommand]] = GetMyCommands
      .of()
      .withScope(BotCommandScopeAllPrivateChats.of())
      .withLanguageCode("EN")
  }

  "SetMyCommands" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "getMyCommands"
      }
    }

    "parameters" should {
      "represent parameters as form data" in new Scope {
        command.parameters shouldBe MultipartBody.of(
          stringPart("scope", jsonResourceString("json/command/bot-command-scope.json")),
          stringPart("language_code", "EN")
        )
      }
    }
  }
}
