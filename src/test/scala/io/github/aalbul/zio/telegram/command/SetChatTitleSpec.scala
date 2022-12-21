package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.SetChatTitle.SetChatTitlePayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class SetChatTitleSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = SetChatTitle.of(chatId = "9932", title = "new title")
    val payload: SetChatTitlePayload = SetChatTitlePayload(chatId = "9932", title = "new title")
  }

  "SetChatTitle" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "setChatTitle"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "SetChatTitlePayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/set-chat-title-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[SetChatTitlePayload]("json/command/set-chat-title-payload.json") shouldBe payload
        }
      }
    }
  }
}
