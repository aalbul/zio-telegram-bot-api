package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class VideoNoteSpec extends BaseSpec {
  "VideoNote" when {
    "encoder" should {
      "encode video node to json" in {
        writeToString(videoNote1) should matchJsonResource("json/model/video-note.json")
      }
    }

    "decoder" should {
      "decode video node from json" in {
        jsonResourceAs[VideoNote]("json/model/video-note.json") shouldBe videoNote1
      }
    }
  }
}
