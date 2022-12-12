package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class VideoChatStartedSpec extends BaseSpec {
  "VideoChatStarted" when {
    "decoder" should {
      "decode video chat started json" in {
        jsonResourceAs[VideoChatStarted]("json/model/video-chat-started.json") shouldBe videoChatStarted1
      }
    }
  }
}
