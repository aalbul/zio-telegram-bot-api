package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.EditForumTopic.EditForumTopicPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class EditForumTopicSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] =
      EditForumTopic
        .of(chatId = "412", messageThreadId = 112)
        .withName("General")
        .withIconCustomEmojiId("emoji1")
    val payload: EditForumTopicPayload = EditForumTopicPayload(
      chatId = "412",
      messageThreadId = 112,
      name = Some("General"),
      iconCustomEmojiId = Some("emoji1")
    )
  }

  "EditForumTopic" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "editForumTopic"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "EditForumTopicPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/edit-forum-topic-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[EditForumTopicPayload](
            "json/command/edit-forum-topic-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
