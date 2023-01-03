package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.UnhideGeneralForumTopic.UnhideGeneralForumTopicPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class UnhideGeneralForumTopicSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = UnhideGeneralForumTopic.of(chatId = "139")
    val payload: UnhideGeneralForumTopicPayload = UnhideGeneralForumTopicPayload(chatId = "139")
  }

  "UnhideGeneralForumTopic" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "unhideGeneralForumTopic"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "UnhideGeneralForumTopicPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/unhide-general-forum-topic-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[UnhideGeneralForumTopicPayload](
            "json/command/unhide-general-forum-topic-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
