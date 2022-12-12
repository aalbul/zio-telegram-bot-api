package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class VideoNoteSpec extends BaseSpec {
  "VideoNote" when {
    "decoder" should {
      "decode video node json" in {
        jsonResourceAs[VideoNote]("json/model/video-note.json") shouldBe videoNote1
      }
    }
  }
}
