package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class VideoChatScheduledSpec extends BaseSpec {
  "VideoChatScheduled" when {
    "encoder" should {
      "encode video chat scheduled to json" in {
        writeToString(videoChatScheduled1) should matchJsonResource(
          "json/model/video-chat-scheduled.json"
        )
      }
    }

    "decoder" should {
      "decode video chat scheduled from json" in {
        jsonResourceAs[VideoChatScheduled]("json/model/video-chat-scheduled.json") shouldBe videoChatScheduled1
      }
    }
  }
}
