package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.GetChatAdministrators.GetChatAdministratorsPayload
import io.github.aalbul.zio.telegram.domain.ChatMember
import io.github.aalbul.zio.telegram.test.BaseSpec

class GetChatAdministratorsSpec extends BaseSpec {
  trait Scope {
    val command: Command[Seq[ChatMember]] = GetChatAdministrators.of(chatId = "551")
    val payload: GetChatAdministratorsPayload = GetChatAdministratorsPayload(chatId = "551")
  }

  "GetChatAdministrators" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "getChatAdministrators"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "GetChatAdministratorsPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/get-chat-administrators-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[GetChatAdministratorsPayload](
            "json/command/get-chat-administrators-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
