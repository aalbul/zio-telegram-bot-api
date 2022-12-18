package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.GetChat.GetChatPayload
import io.github.aalbul.zio.telegram.domain.Chat
import io.github.aalbul.zio.telegram.test.BaseSpec

class GetChatSpec extends BaseSpec {
  trait Scope {
    val command: Command[Chat] = GetChat.of("557")

    val payload: GetChatPayload = GetChatPayload(chatId = "557")
  }

  "GetChat" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "getChat"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "GetChatPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/get-chat-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[GetChatPayload]("json/command/get-chat-payload.json") shouldBe payload
        }
      }
    }
  }
}
