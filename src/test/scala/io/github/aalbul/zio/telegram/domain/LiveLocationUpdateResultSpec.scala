package io.github.aalbul.zio.telegram.domain

import io.circe.syntax.EncoderOps
import io.github.aalbul.zio.telegram.test.BaseSpec

class LiveLocationUpdateResultSpec extends BaseSpec {
  "LiveLocationUpdateResult" when {
    "decoder" should {
      "decode inline message updated result" in {
        true.asJson.as[LiveLocationUpdateResult] shouldBe Right(
          LiveLocationUpdateResult(
            message = None,
            inlineUpdated = true
          )
        )
      }

      "decode message updated result" in {
        jsonResourceAs[LiveLocationUpdateResult]("json/model/message.json") shouldBe LiveLocationUpdateResult(
          message = Some(message1),
          inlineUpdated = false
        )
      }
    }
  }
}
