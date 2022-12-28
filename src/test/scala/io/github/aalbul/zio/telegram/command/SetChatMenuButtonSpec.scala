package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.SetChatMenuButton.SetChatMenuButtonPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class SetChatMenuButtonSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] =
      SetChatMenuButton
        .of()
        .withChatId("553")
        .withMenuButton(menuButton1)

    val payload: SetChatMenuButtonPayload = SetChatMenuButtonPayload(
      chatId = Some("553"),
      menuButton = Some(menuButton1)
    )
  }

  "SetChatMenuButton" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "setChatMenuButton"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "SetChatMenuButtonPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/set-chat-menu-button-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[SetChatMenuButtonPayload]("json/command/set-chat-menu-button-payload.json") shouldBe payload
        }
      }
    }
  }
}
