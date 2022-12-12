package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class VideoSpec extends BaseSpec {
  "Video" when {
    "decoder" should {
      "decode video from json" in {
        jsonResourceAs[Video]("json/model/video.json") shouldBe video1
      }
    }
  }
}
