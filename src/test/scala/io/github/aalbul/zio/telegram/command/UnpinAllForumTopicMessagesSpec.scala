package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.UnpinAllForumTopicMessages.UnpinAllForumTopicMessagesPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class UnpinAllForumTopicMessagesSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = UnpinAllForumTopicMessages.of(chatId = "514", messageThreadId = 443)
    val payload: UnpinAllForumTopicMessagesPayload =
      UnpinAllForumTopicMessagesPayload(chatId = "514", messageThreadId = 443)
  }

  "UnpinAllForumTopicMessages" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "unpinAllForumTopicMessages"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "UnpinAllForumTopicMessagesPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/unpin-all-forum-topic-messages-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[UnpinAllForumTopicMessagesPayload](
            "json/command/unpin-all-forum-topic-messages-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
