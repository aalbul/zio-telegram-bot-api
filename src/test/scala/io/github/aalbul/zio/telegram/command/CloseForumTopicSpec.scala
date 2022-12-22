package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.CloseForumTopic.CloseForumTopicPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class CloseForumTopicSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = CloseForumTopic.of(chatId = "512", messageThreadId = 441)
    val payload: CloseForumTopicPayload = CloseForumTopicPayload(chatId = "512", messageThreadId = 441)
  }

  "CloseForumTopic" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "closeForumTopic"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "CloseForumTopicPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/close-forum-topic-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[CloseForumTopicPayload]("json/command/close-forum-topic-payload.json") shouldBe payload
        }
      }
    }
  }
}
