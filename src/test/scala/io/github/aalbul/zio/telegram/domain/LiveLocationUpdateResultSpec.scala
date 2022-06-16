package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec
import io.circe.parser
import io.circe.syntax.EncoderOps

class LiveLocationUpdateResultSpec extends BaseSpec {
  "LiveLocationUpdateResult" when {
    "decoder" should {
      "decode inline message updated result" in {
        parser.parse(true.asJson.toString()).flatMap(_.as[LiveLocationUpdateResult]) shouldBe Right(
          LiveLocationUpdateResult(
            message = None,
            inlineUpdated = Some(true)
          )
        )
      }

      "decode message updated result" in {
        jsonResource("json/entity/message.json").as[LiveLocationUpdateResult] shouldBe Right(
          LiveLocationUpdateResult(
            message = Some(message1),
            inlineUpdated = None
          )
        )
      }
    }
  }
}
