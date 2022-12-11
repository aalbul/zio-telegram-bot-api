package io.github.aalbul.zio.telegram.domain

import io.circe.Json
import io.circe.syntax.EncoderOps
import io.github.aalbul.zio.telegram.test.BaseSpec

class ParseModesSpec extends BaseSpec {
  "ParseModes" when {
    "encoder" should {
      "encode ParseMode to json" in {
        ParseModes.MarkdownV2.asJson shouldBe Json.fromString("MarkdownV2")
        ParseModes.HTML.asJson shouldBe Json.fromString("HTML")
      }
    }
  }
}
