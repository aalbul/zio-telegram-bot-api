package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import io.github.aalbul.zio.telegram.domain.BotCommandScopeAllPrivateChats
import io.github.aalbul.zio.telegram.test.BaseSpec

class DeleteMyCommandsSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = DeleteMyCommands
      .of()
      .withScope(BotCommandScopeAllPrivateChats.of())
      .withLanguageCode("EN")
  }

  "DeleteMyCommands" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "deleteMyCommands"
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
