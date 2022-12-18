package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class VideoChatParticipantsInvitedSpec extends BaseSpec {
  "VideoChatParticipantsInvited" when {
    "encoder" should {
      "encode video chat participants invited to json" in {
        writeToString(videoChatParticipantsInvited1) should matchJsonResource(
          "json/model/video-chat-participants-invited.json"
        )
      }
    }

    "decoder" should {
      "decode video chat participants invited from json" in {
        jsonResourceAs[VideoChatParticipantsInvited](
          "json/model/video-chat-participants-invited.json"
        ) shouldBe videoChatParticipantsInvited1
      }
    }
  }
}
