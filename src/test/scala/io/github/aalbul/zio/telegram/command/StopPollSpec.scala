package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.StopPoll.StopPollPayload
import io.github.aalbul.zio.telegram.domain.Poll
import io.github.aalbul.zio.telegram.test.BaseSpec

class StopPollSpec extends BaseSpec {
  trait Scope {
    val command: Command[Poll] =
      StopPoll
        .of(chatId = "361", messageId = 211)
        .withReplyMarkup(inlineKeyboardMarkup1)

    val payload: StopPollPayload = StopPollPayload(
      chatId = "361",
      messageId = 211,
      replyMarkup = Some(inlineKeyboardMarkup1)
    )
  }

  "StopPoll" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "stopPoll"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "StopPollPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/stop-poll-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[StopPollPayload](
            "json/command/stop-poll-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
