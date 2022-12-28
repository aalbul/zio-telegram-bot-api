package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.GetChatMenuButton.GetChatMenuButtonPayload
import io.github.aalbul.zio.telegram.domain.MenuButton
import io.github.aalbul.zio.telegram.test.BaseSpec

class GetChatMenuButtonSpec extends BaseSpec {
  trait Scope {
    val command: Command[MenuButton] =
      GetChatMenuButton
        .of
        .withChatId("554")

    val payload: GetChatMenuButtonPayload = GetChatMenuButtonPayload(chatId = Some("554"))
  }

  "GetChatMenuButton" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "getChatMenuButton"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "GetChatMenuButtonPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/get-chat-menu-button-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[GetChatMenuButtonPayload]("json/command/get-chat-menu-button-payload.json") shouldBe payload
        }
      }
    }
  }
}
