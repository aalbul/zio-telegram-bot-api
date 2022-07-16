package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.test.BaseSpec
import StickerMessage.stickerMessageProjector

class StickerMessageSpec extends BaseSpec {
  "StickerMessage" when {
    "stickerMessageProjector" should {
      "properly project sticker messages" in {
        stickerMessageProjector.project(stickerMessage1) shouldBe Some(stickerMessageProjection)
      }

      "not match any other messages" in {
        (allMessages - stickerMessage1).flatMap(stickerMessageProjector.project) shouldBe empty
      }
    }
  }
}
