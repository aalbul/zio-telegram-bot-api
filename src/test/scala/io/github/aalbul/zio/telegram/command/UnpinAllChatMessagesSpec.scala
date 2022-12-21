package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.UnpinAllChatMessages.UnpinAllChatMessagesPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class UnpinAllChatMessagesSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = UnpinAllChatMessages.of(chatId = "277")
    val payload: UnpinAllChatMessagesPayload = UnpinAllChatMessagesPayload(chatId = "277")
  }

  "UnpinAllChatMessages" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "unpinAllChatMessages"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "UnpinAllChatMessagesPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource(
            "json/command/unpin-all-chat-messages-payload.json"
          )
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[UnpinAllChatMessagesPayload](
            "json/command/unpin-all-chat-messages-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
