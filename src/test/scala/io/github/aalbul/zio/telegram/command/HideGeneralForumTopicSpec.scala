package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.HideGeneralForumTopic.HideGeneralForumTopicPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class HideGeneralForumTopicSpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = HideGeneralForumTopic.of(chatId = "138")
    val payload: HideGeneralForumTopicPayload = HideGeneralForumTopicPayload(chatId = "138")
  }

  "HideGeneralForumTopic" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "hideGeneralForumTopic"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "HideGeneralForumTopicPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/hide-general-forum-topic-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[HideGeneralForumTopicPayload](
            "json/command/hide-general-forum-topic-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
