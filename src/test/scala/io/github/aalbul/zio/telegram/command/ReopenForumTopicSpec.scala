package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.ReopenForumTopic.ReopenForumTopicPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class ReopenForumTopicSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = ReopenForumTopic.of(chatId = "513", messageThreadId = 442)
    val payload: ReopenForumTopicPayload = ReopenForumTopicPayload(chatId = "513", messageThreadId = 442)
  }

  "ReopenForumTopic" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "reopenForumTopic"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "ReopenForumTopicPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/reopen-forum-topic-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[ReopenForumTopicPayload]("json/command/reopen-forum-topic-payload.json") shouldBe payload
        }
      }
    }
  }
}
