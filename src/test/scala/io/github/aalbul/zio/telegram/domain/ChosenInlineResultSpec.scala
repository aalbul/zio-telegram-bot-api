package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class ChosenInlineResultSpec extends BaseSpec {
  "ChosenInlineResult" when {
    "decoder" should {
      "should decode chosen inline result json" in {
        jsonResourceAs[ChosenInlineResult]("json/model/chosen-inline-result.json") shouldBe chosenInlineResult1
      }
    }
  }
}
