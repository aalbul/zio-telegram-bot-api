package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.{readFromString, writeToString}
import io.github.aalbul.zio.telegram.test.BaseSpec

class MessageOrInlineMessageUpdateResultSpec extends BaseSpec {
  "MessageOrInlineMessageUpdateResult" when {
    "encoder" should {
      "encode inline message updated result to json" in {
        writeToString(
          MessageOrInlineMessageUpdateResult(
            message = None,
            inlineUpdated = true
          )
        ) shouldBe "true"
      }

      "encode message updated result to json" in {
        writeToString(
          MessageOrInlineMessageUpdateResult(
            message = Some(message1),
            inlineUpdated = false
          )
        ) should matchJsonResource("json/model/message.json")
      }
    }

    "decoder" should {
      "decode inline message updated result from json" in {
        readFromString[MessageOrInlineMessageUpdateResult]("true") shouldBe MessageOrInlineMessageUpdateResult(
          message = None,
          inlineUpdated = true
        )
      }

      "decode message updated result from json" in {
        jsonResourceAs[MessageOrInlineMessageUpdateResult]("json/model/message.json") shouldBe MessageOrInlineMessageUpdateResult(
          message = Some(message1),
          inlineUpdated = false
        )
      }
    }
  }
}
