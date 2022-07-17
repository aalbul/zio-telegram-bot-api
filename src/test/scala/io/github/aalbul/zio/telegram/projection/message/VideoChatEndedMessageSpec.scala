package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.test.BaseSpec
import VideoChatEndedMessage.videoChatEndedProjector

class VideoChatEndedMessageSpec extends BaseSpec {
  "VideoChatEndedMessage" when {
    "videoChatEndedProjector" should {
      "properly project video chat ended messages" in {
        videoChatEndedProjector.project(videoChatEndedMessage1) shouldBe Some(videoChatEndedProjection)
      }

      "not match any other messages" in {
        (allMessages - videoChatEndedMessage1).flatMap(videoChatEndedProjector.project) shouldBe empty
      }
    }
  }
}
