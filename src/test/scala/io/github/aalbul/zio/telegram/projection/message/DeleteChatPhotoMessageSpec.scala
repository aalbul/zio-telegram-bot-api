package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.test.BaseSpec
import DeleteChatPhotoMessage.deleteChatPhotoProjector

class DeleteChatPhotoMessageSpec extends BaseSpec {
  "DeleteChatPhotoMessage" when {
    "deleteChatPhotoProjector" should {
      "properly project dice messages" in {
        deleteChatPhotoProjector.project(deleteChatPhotoMessage1) shouldBe Some(deleteChatPhotoProjection)
      }

      "not match any other messages" in {
        (allMessages - deleteChatPhotoMessage1).flatMap(deleteChatPhotoProjector.project) shouldBe empty
      }
    }
  }
}
