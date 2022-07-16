package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.test.BaseSpec
import VideoNoteMessage.videoNoteMessageProjector

class VideoNoteMessageSpec extends BaseSpec {
  "VideoNoteMessage" when {
    "videoNoteMessageProjector" should {
      "properly project video note messages" in {
        videoNoteMessageProjector.project(videoNoteMessage1) shouldBe Some(videoNoteMessageProjection)
      }

      "not match any other messages" in {
        (allMessages - videoNoteMessage1).flatMap(videoNoteMessageProjector.project) shouldBe empty
      }
    }
  }
}
