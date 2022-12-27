package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.AnswerCallbackQuery.AnswerCallbackQueryPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

import scala.concurrent.duration.DurationInt

class AnswerCallbackQuerySpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = AnswerCallbackQuery
      .of(callbackQueryId = "223")
      .withText("query text")
      .withShowAlert(true)
      .withUrl("https://google.com/callback")
      .withCacheTime(30.minutes)

    val payload: AnswerCallbackQueryPayload = AnswerCallbackQueryPayload(
      callbackQueryId = "223",
      text = Some("query text"),
      showAlert = Some(true),
      url = Some("https://google.com/callback"),
      cacheTime = Some(30.minutes)
    )
  }

  "AnswerCallbackQuery" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "answerCallbackQuery"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "AnswerCallbackQueryPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/answer-callback-query-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[AnswerCallbackQueryPayload](
            "json/command/answer-callback-query-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
