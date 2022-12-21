package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.CreateForumTopic.CreateForumTopicPayload
import io.github.aalbul.zio.telegram.domain.ForumTopic
import io.github.aalbul.zio.telegram.test.BaseSpec

class CreateForumTopicSpec extends BaseSpec {
  trait Scope {
    val command: Command[ForumTopic] = CreateForumTopic
      .of(chatId = "701", name = "General")
      .withIconColor(104)
      .withIconCustomEmojiId("emoji1")
    val payload: CreateForumTopicPayload = CreateForumTopicPayload(
      chatId = "701",
      name = "General",
      iconColor = Some(104),
      iconCustomEmojiId = Some("emoji1")
    )
  }

  "CreateForumTopic" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "createForumTopic"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "CreateForumTopicPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/create-forum-topic-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[CreateForumTopicPayload](
            "json/command/create-forum-topic-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
