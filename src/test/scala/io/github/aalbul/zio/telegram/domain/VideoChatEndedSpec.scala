package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class VideoChatEndedSpec extends BaseSpec {
  "VideoChatEnded" when {
    "decoder" should {
      "decode video chat ended json" in {
        jsonResourceAs[VideoChatEnded]("json/model/video-chat-ended.json") shouldBe videoChatEnded1
      }
    }
  }
}
