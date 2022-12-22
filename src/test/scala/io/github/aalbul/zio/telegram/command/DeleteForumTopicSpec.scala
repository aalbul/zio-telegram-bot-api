package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.DeleteForumTopic.DeleteForumTopicPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class DeleteForumTopicSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = DeleteForumTopic.of(chatId = "513", messageThreadId = 442)
    val payload: DeleteForumTopicPayload = DeleteForumTopicPayload(chatId = "513", messageThreadId = 442)
  }

  "DeleteForumTopic" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "deleteForumTopic"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "DeleteForumTopicPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/delete-forum-topic-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[DeleteForumTopicPayload]("json/command/delete-forum-topic-payload.json") shouldBe payload
        }
      }
    }
  }
}
