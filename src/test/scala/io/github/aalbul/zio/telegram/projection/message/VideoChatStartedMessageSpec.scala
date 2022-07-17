package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.test.BaseSpec
import VideoChatStartedMessage.videoChatStartedProjector

class VideoChatStartedMessageSpec extends BaseSpec {
  "VideoChatStartedMessage" when {
    "videoChatStartedProjector" should {
      "properly project video chat started messages" in {
        videoChatStartedProjector.project(videoChatStartedMessage1) shouldBe Some(videoChatStartedProjection)
      }

      "not match any other messages" in {
        (allMessages - videoChatStartedMessage1).flatMap(videoChatStartedProjector.project) shouldBe empty
      }
    }
  }
}
