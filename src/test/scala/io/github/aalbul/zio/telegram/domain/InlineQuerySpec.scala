package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class InlineQuerySpec extends BaseSpec {
  "InlineQuery" when {
    "decoder" should {
      "should decode inline query json" in {
        jsonResourceAs[InlineQuery]("json/model/inline-query.json") shouldBe inlineQuery1
      }
    }
  }
}
