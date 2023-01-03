package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.EditGeneralForumTopic.EditGeneralForumTopicPayload
import io.github.aalbul.zio.telegram.command.EditMessageCaption.EditMessageCaptionPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class EditGeneralForumTopicSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = EditGeneralForumTopic.of(chatId = "127", name = "topic 2")
    val payload: EditGeneralForumTopicPayload = EditGeneralForumTopicPayload(chatId = "127", name = "topic 2")
  }

  "EditGeneralForumTopic" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "editGeneralForumTopic"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "EditGeneralForumTopicPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/edit-general-forum-topic-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[EditGeneralForumTopicPayload](
            "json/command/edit-general-forum-topic-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
