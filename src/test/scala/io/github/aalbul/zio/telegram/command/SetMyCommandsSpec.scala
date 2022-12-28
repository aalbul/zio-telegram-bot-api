package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import io.github.aalbul.zio.telegram.domain.BotCommandScopeAllPrivateChats
import io.github.aalbul.zio.telegram.test.BaseSpec

class SetMyCommandsSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = SetMyCommands
      .of(Seq(botCommand1, botCommand2))
      .withScope(BotCommandScopeAllPrivateChats.of())
      .withLanguageCode("EN")
  }

  "SetMyCommands" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "setMyCommands"
      }
    }

    "parameters" should {
      "represent parameters as form data" in new Scope {
        command.parameters shouldBe MultipartBody.of(
          stringPart("commands", jsonResourceString("json/command/bot-commands.json")),
          stringPart("scope", jsonResourceString("json/command/bot-command-scope.json")),
          stringPart("language_code", "EN")
        )
      }
    }
  }
}
