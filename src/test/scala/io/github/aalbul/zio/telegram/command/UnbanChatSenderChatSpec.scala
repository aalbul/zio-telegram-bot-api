package io.github.aalbul.zio.telegram.command

import io.circe.syntax.EncoderOps
import io.github.aalbul.zio.telegram.command.UnbanChatSenderChat.UnbanChatSenderChatPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class UnbanChatSenderChatSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = UnbanChatSenderChat.of(chatId = "122", senderChatId = 662)

    val payload: UnbanChatSenderChatPayload = UnbanChatSenderChatPayload(
      chatId = "122",
      senderChatId = 662
    )
  }

  "UnbanChatSenderChat" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "unbanChatSenderChat"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "UnbanChatSenderChatPayload" should {
      "serialize payload to json" in new Scope {
        payload.asJson shouldBe jsonResource("json/command/unban-chat-sender-chat-payload.json")
      }
    }
  }
}
