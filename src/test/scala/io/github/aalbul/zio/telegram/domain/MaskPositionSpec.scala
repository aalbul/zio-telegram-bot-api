package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class MaskPositionSpec extends BaseSpec {
  "MaskPosition" when {
    "decoder" should {
      "decode mask position json" in {
        jsonResourceAs[MaskPosition]("json/model/mask-position.json") shouldBe maskPosition1
      }
    }
  }
}
