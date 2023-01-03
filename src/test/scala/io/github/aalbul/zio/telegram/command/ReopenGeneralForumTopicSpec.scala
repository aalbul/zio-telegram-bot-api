package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.ReopenGeneralForumTopic.ReopenGeneralForumTopicPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class ReopenGeneralForumTopicSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = ReopenGeneralForumTopic.of(chatId = "120")
    val payload: ReopenGeneralForumTopicPayload = ReopenGeneralForumTopicPayload(chatId = "120")
  }

  "ReopenGeneralForumTopic" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "reopenGeneralForumTopic"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "ReopenGeneralForumTopicPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/reopen-general-forum-topic-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[ReopenGeneralForumTopicPayload](
            "json/command/reopen-general-forum-topic-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
