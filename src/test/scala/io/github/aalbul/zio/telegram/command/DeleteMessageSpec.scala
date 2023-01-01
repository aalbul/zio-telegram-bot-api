package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.DeleteMessage.DeleteMessagePayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class DeleteMessageSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = DeleteMessage.of(chatId = "389", messageId = 112)

    val payload: DeleteMessagePayload = DeleteMessagePayload(
      chatId = "389",
      messageId = 112
    )
  }

  "DeleteMessage" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "deleteMessage"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "DeleteMessagePayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/delete-message-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[DeleteMessagePayload](
            "json/command/delete-message-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
