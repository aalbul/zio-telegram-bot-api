package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.{readFromString, writeToString}
import io.github.aalbul.zio.telegram.test.BaseSpec

class LiveLocationUpdateResultSpec extends BaseSpec {
  "LiveLocationUpdateResult" when {
    "encoder" should {
      "encode inline message updated result to json" in {
        writeToString(
          LiveLocationUpdateResult(
            message = None,
            inlineUpdated = true
          )
        ) shouldBe "true"
      }

      "encode message updated result to json" in {
        writeToString(
          LiveLocationUpdateResult(
            message = Some(message1),
            inlineUpdated = false
          )
        ) should matchJsonResource("json/model/message.json")
      }
    }

    "decoder" should {
      "decode inline message updated result from json" in {
        readFromString[LiveLocationUpdateResult]("true") shouldBe LiveLocationUpdateResult(
          message = None,
          inlineUpdated = true
        )
      }

      "decode message updated result from json" in {
        jsonResourceAs[LiveLocationUpdateResult]("json/model/message.json") shouldBe LiveLocationUpdateResult(
          message = Some(message1),
          inlineUpdated = false
        )
      }
    }
  }
}
