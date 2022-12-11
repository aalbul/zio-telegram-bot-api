package io.github.aalbul.zio.telegram.domain

import io.circe.Json
import io.circe.syntax.EncoderOps
import io.github.aalbul.zio.telegram.domain.PollTypes.PollType
import io.github.aalbul.zio.telegram.test.BaseSpec

class PollTypesSpec extends BaseSpec {
  "decoder" should {
    "decode poll types from json" in {
      Json.fromString("quiz").as[PollType] shouldBe Right(PollTypes.Quiz)
      Json.fromString("regular").as[PollType] shouldBe Right(PollTypes.Regular)
    }
  }

  "encoder" should {
    "encode poll types to json" in {
      PollTypes.Quiz.asJson shouldBe Json.fromString("quiz")
      PollTypes.Regular.asJson shouldBe Json.fromString("regular")
    }
  }
}
