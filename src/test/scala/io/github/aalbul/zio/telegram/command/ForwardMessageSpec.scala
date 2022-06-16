package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.ForwardMessage.ForwardMessagePayload
import io.github.aalbul.zio.telegram.domain.Message
import io.github.aalbul.zio.telegram.test.BaseSpec
import io.circe.syntax.EncoderOps

class ForwardMessageSpec extends BaseSpec {
  trait Scope {
    val command: Command[Message] = ForwardMessage
      .of(13, "5593", "9048")
      .withDisableNotification(true)
      .withProtectContent(false)

    val payload: ForwardMessagePayload = ForwardMessagePayload(
      chatId = "9048",
      fromChatId = "5593",
      messageId = 13,
      disableNotification = Some(true),
      protectContent = Some(false)
    )
  }

  "ForwardMessage" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "forwardMessage"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "ForwardMessagePayload" should {
      "serialize payload to json" in new Scope {
        payload.asJson shouldBe jsonResource("json/command/forward-message-payload.json")
      }
    }
  }
}
