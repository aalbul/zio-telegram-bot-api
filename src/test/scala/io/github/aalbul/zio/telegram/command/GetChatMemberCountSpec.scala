package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.GetChatMemberCount.GetChatMemberCountPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class GetChatMemberCountSpec extends BaseSpec {
  trait Scope {
    val command: Command[Long] = GetChatMemberCount.of(chatId = "552")
    val payload: GetChatMemberCountPayload = GetChatMemberCountPayload(chatId = "552")
  }

  "GetChatMemberCount" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "getChatMemberCount"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "GetChatMemberCountPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/get-chat-member-count-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[GetChatMemberCountPayload](
            "json/command/get-chat-member-count-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
