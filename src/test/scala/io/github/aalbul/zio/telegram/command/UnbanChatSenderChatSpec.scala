package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
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
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource(
            "json/command/unban-chat-sender-chat-payload.json"
          )
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[UnbanChatSenderChatPayload](
            "json/command/unban-chat-sender-chat-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
