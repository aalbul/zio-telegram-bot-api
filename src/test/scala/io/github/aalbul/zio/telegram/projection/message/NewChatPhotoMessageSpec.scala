package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.test.BaseSpec
import NewChatPhotoMessage.newChatPhotoProjector

class NewChatPhotoMessageSpec extends BaseSpec {
  "NewChatPhotoMessage" when {
    "newChatPhotoProjector" should {
      "properly project new chat photo messages" in {
        newChatPhotoProjector.project(newChatPhotoMessage1) shouldBe Some(newChatPhotoProjection)
      }

      "not match any other messages" in {
        (allMessages - newChatPhotoMessage1).flatMap(newChatPhotoProjector.project) shouldBe empty
      }
    }
  }
}
