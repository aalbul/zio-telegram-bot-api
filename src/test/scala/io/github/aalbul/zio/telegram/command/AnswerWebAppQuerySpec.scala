package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.AnswerWebAppQuery.AnswerWebAppQueryPayload
import io.github.aalbul.zio.telegram.domain.SentWebAppMessage
import io.github.aalbul.zio.telegram.test.BaseSpec

class AnswerWebAppQuerySpec extends BaseSpec {
  trait Scope {
    val command: Command[SentWebAppMessage] = AnswerWebAppQuery.of(webAppQueryId = "113", result = inlineQueryResult1)

    val payload: AnswerWebAppQueryPayload = AnswerWebAppQueryPayload(
      webAppQueryId = "113",
      result = inlineQueryResult1
    )
  }

  "AnswerWebAppQuery" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "answerWebAppQuery"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "AnswerWebAppQueryPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/answer-web-ppp-query-payload.json")
        }
      }
    }
  }
}
