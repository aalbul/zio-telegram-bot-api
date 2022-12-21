package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.ApproveChatJoinRequest.ApproveChatJoinRequestPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class ApproveChatJoinRequestSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = ApproveChatJoinRequest.of(chatId = "352", userId = 41)
    val payload: ApproveChatJoinRequestPayload = ApproveChatJoinRequestPayload(chatId = "352", userId = 41)
  }

  "ApproveChatJoinRequest" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "approveChatJoinRequest"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "ApproveChatJoinRequestPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/approve-chat-join-request-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[ApproveChatJoinRequestPayload](
            "json/command/approve-chat-join-request-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
