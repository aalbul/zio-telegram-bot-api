package io.github.aalbul.zio.telegram.domain

import io.circe.syntax.EncoderOps
import io.github.aalbul.zio.telegram.test.BaseSpec

class InputMediaVideoSpec extends BaseSpec {
  "InputMediaVideo" when {
    "encoder" should {
      "should encode input media video to json" in {
        inputMediaVideo1.asJson shouldBe jsonResource("json/model/input-media-video.json")
      }
    }
  }
}
