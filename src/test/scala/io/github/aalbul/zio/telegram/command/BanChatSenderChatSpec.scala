package io.github.aalbul.zio.telegram.command

import io.circe.syntax.EncoderOps
import io.github.aalbul.zio.telegram.command.BanChatSenderChat.BanChatSenderChatPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class BanChatSenderChatSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = BanChatSenderChat.of(chatId = "121", senderChatId = 661)

    val payload: BanChatSenderChatPayload = BanChatSenderChatPayload(
      chatId = "121",
      senderChatId = 661
    )
  }

  "BanChatSenderChat" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "banChatSenderChat"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "BanChatSenderChatPayload" should {
      "serialize payload to json" in new Scope {
        payload.asJson shouldBe jsonResource("json/command/ban-chat-sender-chat-payload.json")
      }
    }
  }
}
