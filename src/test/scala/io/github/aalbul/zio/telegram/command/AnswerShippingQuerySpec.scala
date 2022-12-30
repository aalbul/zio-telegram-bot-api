package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.AnswerShippingQuery.AnswerShippingQueryPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class AnswerShippingQuerySpec extends BaseSpec {
  trait Scope {
    val command: Command[Boolean] = AnswerShippingQuery
      .of(shippingQueryId = "query-1", ok = true)
      .withShippingOptions(Seq(shippingOption1))
      .withErrorMessage("some error")

    val payload: AnswerShippingQueryPayload = AnswerShippingQueryPayload(
      shippingQueryId = "query-1",
      ok = true,
      shippingOptions = Some(Seq(shippingOption1)),
      errorMessage = Some("some error")
    )
  }

  "AnswerShippingQuery" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "answerShippingQuery"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "AnswerShippingQueryPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/answer-shipping-query-payload.json")
        }
      }
    }
  }
}
