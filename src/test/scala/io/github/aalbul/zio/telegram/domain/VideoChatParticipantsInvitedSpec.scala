package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class VideoChatParticipantsInvitedSpec extends BaseSpec {
  "VideoChatParticipantsInvited" when {
    "decoder" should {
      "decode video chat participants invited json" in {
        jsonResourceAs[VideoChatParticipantsInvited](
          "json/model/video-chat-participants-invited.json"
        ) shouldBe videoChatParticipantsInvited1
      }
    }
  }
}
