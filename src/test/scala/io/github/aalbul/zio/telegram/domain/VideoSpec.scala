package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class VideoSpec extends BaseSpec {
  "Video" when {
    "encoder" should {
      "encode video to json" in {
        writeToString(video1) should matchJsonResource("json/model/video.json")
      }
    }

    "decoder" should {
      "decode video from json" in {
        jsonResourceAs[Video]("json/model/video.json") shouldBe video1
      }
    }
  }
}
