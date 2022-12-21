package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.PinChatMessage.PinChatMessagePayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class PinChatMessageSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = PinChatMessage
      .of(chatId = "662", messageId = 123)
      .withDisableNotification(true)
    val payload: PinChatMessagePayload = PinChatMessagePayload(
      chatId = "662",
      messageId = 123,
      disableNotification = Some(true)
    )
  }

  "PinChatMessage" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "pinChatMessage"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "PinChatMessagePayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/pin-chat-message-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[PinChatMessagePayload]("json/command/pin-chat-message-payload.json") shouldBe payload
        }
      }
    }
  }
}
