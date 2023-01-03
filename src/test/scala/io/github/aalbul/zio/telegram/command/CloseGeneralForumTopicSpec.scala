package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.CloseGeneralForumTopic.CloseGeneralForumTopicPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class CloseGeneralForumTopicSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = CloseGeneralForumTopic.of(chatId = "128")
    val payload: CloseGeneralForumTopicPayload = CloseGeneralForumTopicPayload(chatId = "128")
  }

  "CloseGeneralForumTopic" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "closeGeneralForumTopic"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "CloseGeneralForumTopicPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/close-general-forum-topic-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[CloseGeneralForumTopicPayload](
            "json/command/close-general-forum-topic-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
