package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.SendChatAction.SendChatActionPayload
import io.github.aalbul.zio.telegram.domain.ChatAction
import io.github.aalbul.zio.telegram.test.BaseSpec

class SendChatActionSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = SendChatAction.of(chatId = "21", action = ChatAction.ChooseSticker)
    val payload: SendChatActionPayload = SendChatActionPayload(chatId = "21", action = ChatAction.ChooseSticker)
  }

  "SendChatAction" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "sendChatAction"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "SendChatActionPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/send-chat-action-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[SendChatActionPayload]("json/command/send-chat-action-payload.json") shouldBe payload
        }
      }
    }
  }
}
