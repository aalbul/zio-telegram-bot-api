package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.UnpinChatMessage.UnpinChatMessagePayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class UnpinChatMessageSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = UnpinChatMessage
      .of(chatId = "254")
      .withMessageId(223)
    val payload: UnpinChatMessagePayload = UnpinChatMessagePayload(chatId = "254", messageId = Some(223))
  }

  "UnpinChatMessage" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "unpinChatMessage"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "UnpinChatMessagePayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource(
            "json/command/unpin-chat-message-payload.json"
          )
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[UnpinChatMessagePayload](
            "json/command/unpin-chat-message-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
