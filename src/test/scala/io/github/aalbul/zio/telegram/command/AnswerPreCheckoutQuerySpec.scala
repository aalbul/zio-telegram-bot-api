package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.AnswerPreCheckoutQuery.AnswerPreCheckoutQueryPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class AnswerPreCheckoutQuerySpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = AnswerPreCheckoutQuery
      .of(preCheckoutQueryId = "query-1", ok = false)
      .withErrorMessage("some error")

    val payload: AnswerPreCheckoutQueryPayload = AnswerPreCheckoutQueryPayload(
      preCheckoutQueryId = "query-1",
      ok = false,
      errorMessage = Some("some error")
    )
  }

  "AnswerPreCheckoutQuery" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "answerPreCheckoutQuery"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "AnswerPreCheckoutQueryPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/answer-pre-checkout-query-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[AnswerPreCheckoutQueryPayload](
            "json/command/answer-pre-checkout-query-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
