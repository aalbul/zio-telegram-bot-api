package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class AnimationSpec extends BaseSpec {
  "Animation" when {
    "encoder" should {
      "encode animation to json" in {
        writeToString(animation1) should matchJsonResource("json/model/animation.json")
      }
    }

    "decoder" should {
      "decode animation from json" in {
        jsonResourceAs[Animation]("json/model/animation.json") shouldBe animation1
      }
    }
  }
}
