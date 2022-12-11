package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class StickerSpec extends BaseSpec {
  "Sticker" when {
    "decoder" should {
      "decode sticker json" in {
        jsonResourceAs[Sticker]("json/model/sticker.json") shouldBe sticker1
      }
    }
  }
}
