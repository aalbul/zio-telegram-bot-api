package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.test.BaseSpec
import VoiceMessage.voiceMessageProjector

class VoiceMessageSpec extends BaseSpec {
  "VoiceMessage" when {
    "voiceMessageProjector" should {
      "properly project voice messages" in {
        voiceMessageProjector.project(voiceMessage1) shouldBe Some(voiceMessageProjection)
      }

      "not match any other messages" in {
        (allMessages - voiceMessage1).flatMap(voiceMessageProjector.project) shouldBe empty
      }
    }
  }
}
