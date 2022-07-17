package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.projection.message.VideoChatParticipantsInvitedMessage.videoChatParticipantsInvitedProjector
import io.github.aalbul.zio.telegram.test.BaseSpec

class VideoChatParticipantsInvitedMessageSpec extends BaseSpec {
  "VideoChatParticipantsInvitedMessage" when {
    "videoChatParticipantsInvitedProjector" should {
      "properly project video chat participants invited messages" in {
        videoChatParticipantsInvitedProjector.project(videoChatParticipantsInvitedMessage1) shouldBe Some(
          videoChatParticipantsInvitedProjection
        )
      }

      "not match any other messages" in {
        (allMessages - videoChatParticipantsInvitedMessage1).flatMap(
          videoChatParticipantsInvitedProjector.project
        ) shouldBe empty
      }
    }
  }
}
