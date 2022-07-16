package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.test.BaseSpec
import PhotoMessage.photoMessageProjector

class PhotoMessageSpec extends BaseSpec {
  "PhotoMessage" when {
    "photoMessageProjector" should {
      "properly project photo messages" in {
        photoMessageProjector.project(photoMessage1) shouldBe Some(photoMessageProjection)
      }

      "not match any other messages" in {
        (allMessages - photoMessage1).flatMap(photoMessageProjector.project) shouldBe empty
      }
    }
  }
}
