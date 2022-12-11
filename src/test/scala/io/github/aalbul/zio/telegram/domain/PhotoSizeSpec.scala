package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class PhotoSizeSpec extends BaseSpec {
  "PhotoSize" when {
    "decoder" should {
      "decode photo size from json" in {
        jsonResourceAs[PhotoSize]("json/model/photo-size.json") shouldBe photoSize1
      }
    }
  }
}
