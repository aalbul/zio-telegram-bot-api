package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class VideoChatStartedSpec extends BaseSpec {
  "VideoChatStarted" when {
    "encoder" should {
      "encode video chat started to json" in {
        writeToString(videoChatStarted1) should matchJsonResource(
          "json/model/video-chat-started.json"
        )
      }
    }

    "decoder" should {
      "decode video chat started from json" in {
        jsonResourceAs[VideoChatStarted]("json/model/video-chat-started.json") shouldBe videoChatStarted1
      }
    }
  }
}
