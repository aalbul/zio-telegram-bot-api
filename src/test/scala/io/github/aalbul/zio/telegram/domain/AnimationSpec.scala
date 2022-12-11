package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class AnimationSpec extends BaseSpec {
  "Animation" when {
    "decoder" should {
      "decode animation json" in {
        jsonResourceAs[Animation]("json/model/animation.json") shouldBe animation1
      }
    }
  }
}
