package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.test.BaseSpec
import AudioMessage.audioMessageProjector

class AudioMessageSpec extends BaseSpec {
  "AudioMessage" when {
    "audioMessageProjector" should {
      "properly project audio messages" in {
        audioMessageProjector.project(audioMessage1) shouldBe Some(audioMessageProjection)
      }

      "not match any other messages" in {
        (allMessages - audioMessage1).flatMap(audioMessageProjector.project) shouldBe empty
      }
    }
  }
}
