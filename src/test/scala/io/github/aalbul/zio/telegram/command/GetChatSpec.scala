package io.github.aalbul.zio.telegram.command

import io.circe.syntax.EncoderOps
import io.github.aalbul.zio.telegram.command.GetChat.GetChatPayload
import io.github.aalbul.zio.telegram.domain.Chat
import io.github.aalbul.zio.telegram.test.BaseSpec

class GetChatSpec extends BaseSpec {
  trait Scope {
    val command: Command[Chat] = GetChat.of("557")

    val payload: GetChatPayload = GetChatPayload(chatId = "557")
  }

  "GetChat" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "getChat"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "GetChatPayload" should {
      "serialize payload to json" in new Scope {
        payload.asJson shouldBe jsonResource("json/command/get-chat-payload.json")
      }
    }
  }
}
