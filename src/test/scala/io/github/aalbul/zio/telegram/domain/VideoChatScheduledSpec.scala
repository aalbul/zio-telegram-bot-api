package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class VideoChatScheduledSpec extends BaseSpec {
  "VideoChatScheduled" when {
    "decoder" should {
      "decode video chat scheduled json" in {
        jsonResourceAs[VideoChatScheduled]("json/model/video-chat-scheduled.json") shouldBe videoChatScheduled1
      }
    }
  }
}
