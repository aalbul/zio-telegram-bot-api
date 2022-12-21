package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.LeaveChat.LeaveChatPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class LeaveChatSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = LeaveChat.of(chatId = "551")
    val payload: LeaveChatPayload = LeaveChatPayload(chatId = "551")
  }

  "LeaveChat" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "leaveChat"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "LeaveChatPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/leave-chat-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[LeaveChatPayload]("json/command/leave-chat-payload.json") shouldBe payload
        }
      }
    }
  }
}
