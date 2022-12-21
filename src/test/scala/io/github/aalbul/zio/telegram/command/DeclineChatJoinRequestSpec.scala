package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.DeclineChatJoinRequest.DeclineChatJoinRequestPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class DeclineChatJoinRequestSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = DeclineChatJoinRequest.of(chatId = "353", userId = 42)
    val payload: DeclineChatJoinRequestPayload = DeclineChatJoinRequestPayload(chatId = "353", userId = 42)
  }

  "DeclineChatJoinRequest" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "declineChatJoinRequest"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "DeclineChatJoinRequestPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/decline-chat-join-request-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[DeclineChatJoinRequestPayload](
            "json/command/decline-chat-join-request-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
