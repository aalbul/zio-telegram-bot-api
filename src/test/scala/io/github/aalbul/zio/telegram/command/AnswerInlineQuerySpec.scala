package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.AnswerInlineQuery.AnswerInlineQueryPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

import scala.concurrent.duration.DurationInt

class AnswerInlineQuerySpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = AnswerInlineQuery
      .of(inlineQueryId = "113", results = Seq(inlineQueryResult1, inlineQueryResult11))
      .withCacheTime(23.seconds)
      .withIsPersonal(true)
      .withNextOffset("offset-1")
      .withSwitchPmText("switch-pm-text")
      .withSwitchPmParameter("switch-pm-parameter")

    val payload: AnswerInlineQueryPayload = AnswerInlineQueryPayload(
      inlineQueryId = "113",
      results = Seq(inlineQueryResult1, inlineQueryResult11),
      cacheTime = Some(23.seconds),
      isPersonal = Some(true),
      nextOffset = Some("offset-1"),
      switchPmText = Some("switch-pm-text"),
      switchPmParameter = Some("switch-pm-parameter")
    )
  }

  "AnswerInlineQuery" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "answerInlineQuery"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "AnswerInlineQueryPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/answer-inline-query-payload.json")
        }
      }
    }
  }
}
