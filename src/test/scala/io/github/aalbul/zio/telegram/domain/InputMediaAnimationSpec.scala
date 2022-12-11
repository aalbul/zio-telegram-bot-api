package io.github.aalbul.zio.telegram.domain

import io.circe.syntax.EncoderOps
import io.github.aalbul.zio.telegram.test.BaseSpec

class InputMediaAnimationSpec extends BaseSpec {
  "InputMediaAnimation" when {
    "encoder" should {
      "should encode input media animation to json" in {
        inputMediaAnimation1.asJson shouldBe jsonResource("json/model/input-media-animation.json")
      }
    }
  }
}
