package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.test.BaseSpec
import VideoChatScheduledMessage.videoChatScheduledProjector

class VideoChatScheduledMessageSpec extends BaseSpec {
  "VideoChatScheduledMessage" when {
    "videoChatScheduledProjector" should {
      "properly project video chat scheduled messages" in {
        videoChatScheduledProjector.project(videoChatScheduledMessage1) shouldBe Some(videoChatScheduledProjection)
      }

      "not match any other messages" in {
        (allMessages - videoChatScheduledMessage1).flatMap(videoChatScheduledProjector.project) shouldBe empty
      }
    }
  }
}
