package io.github.aalbul.zio.telegram.domain

import io.circe.syntax.EncoderOps
import io.github.aalbul.zio.telegram.test.BaseSpec

class InputMediaPhotoSpec extends BaseSpec {
  "InputMediaPhoto" when {
    "encoder" should {
      "should encode input media photo to json" in {
        inputMediaPhoto1.asJson shouldBe jsonResource("json/model/input-media-photo.json")
      }
    }
  }
}
