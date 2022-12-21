package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.GetChatMember.GetChatMemberPayload
import io.github.aalbul.zio.telegram.domain.ChatMember
import io.github.aalbul.zio.telegram.test.BaseSpec

class GetChatMemberSpec extends BaseSpec {
  trait Scope {
    val command: Command[ChatMember] = GetChatMember.of(chatId = "512", userId = 221)
    val payload: GetChatMemberPayload = GetChatMemberPayload(chatId = "512", userId = 221)
  }

  "GetChatMember" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "getChatMember"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "GetChatMemberPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/get-chat-member-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[GetChatMemberPayload](
            "json/command/get-chat-member-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
