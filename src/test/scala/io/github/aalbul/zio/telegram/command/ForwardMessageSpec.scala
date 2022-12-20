package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.ForwardMessage.ForwardMessagePayload
import io.github.aalbul.zio.telegram.domain.Message
import io.github.aalbul.zio.telegram.test.BaseSpec

class ForwardMessageSpec extends BaseSpec {
  trait Scope {
    val command: Command[Message] = ForwardMessage
      .of(13, "5593", "9048")
      .withMessageThreadId(24)
      .withDisableNotification(true)
      .withProtectContent(false)

    val payload: ForwardMessagePayload = ForwardMessagePayload(
      chatId = "9048",
      messageThreadId = Some(24),
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
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/forward-message-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[ForwardMessagePayload](
            "json/command/forward-message-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
