package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.SetChatDescription.SetChatDescriptionPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class SetChatDescriptionSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = SetChatDescription
      .of("460")
      .withDescription("new description")

    val payload: SetChatDescriptionPayload = SetChatDescriptionPayload(
      chatId = "460",
      description = Some("new description")
    )
  }

  "SetChatDescription" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "setChatDescription"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "SetChatDescriptionPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/set-chat-description-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[SetChatDescriptionPayload]("json/command/set-chat-description-payload.json") shouldBe payload
        }
      }
    }
  }
}
