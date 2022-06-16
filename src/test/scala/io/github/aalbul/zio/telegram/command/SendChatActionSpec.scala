package io.github.aalbul.zio.telegram.command

import io.circe.syntax.EncoderOps
import io.github.aalbul.zio.telegram.command.SendChatAction.SendChatActionPayload
import io.github.aalbul.zio.telegram.domain.ChatActions
import io.github.aalbul.zio.telegram.test.BaseSpec

class SendChatActionSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = SendChatAction.of(chatId = "21", action = ChatActions.ChooseSticker)
    val payload: SendChatActionPayload = SendChatActionPayload(chatId = "21", action = ChatActions.ChooseSticker)
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
      "serialize payload to json" in new Scope {
        payload.asJson shouldBe jsonResource("json/command/send-chat-action-payload.json")
      }
    }
  }
}
