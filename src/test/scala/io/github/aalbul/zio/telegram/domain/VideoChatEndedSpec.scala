package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class VideoChatEndedSpec extends BaseSpec {
  "VideoChatEnded" when {
    "encoder" should {
      "encode video chat ended to json" in {
        writeToString(videoChatEnded1) should matchJsonResource("json/model/video-chat-ended.json")
      }
    }

    "decoder" should {
      "decode video chat ended from json" in {
        jsonResourceAs[VideoChatEnded]("json/model/video-chat-ended.json") shouldBe videoChatEnded1
      }
    }
  }
}
