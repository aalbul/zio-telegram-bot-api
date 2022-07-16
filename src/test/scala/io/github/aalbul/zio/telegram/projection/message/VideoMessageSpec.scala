package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.test.BaseSpec
import VideoMessage.videoMessageProjector

class VideoMessageSpec extends BaseSpec {
  "VideoMessage" when {
    "videoMessageProjector" should {
      "properly project video messages" in {
        videoMessageProjector.project(videoMessage1) shouldBe Some(videoMessageProjection)
      }

      "not match any other messages" in {
        (allMessages - videoMessage1).flatMap(videoMessageProjector.project) shouldBe empty
      }
    }
  }
}
