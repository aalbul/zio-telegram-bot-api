package io.github.aalbul.zio.telegram.projection

import io.github.aalbul.zio.telegram.test.BaseSpec
import EditedMessage.editedMessageProjector

class EditedMessageSpec extends BaseSpec {
  "EditedMessage" when {
    "editedMessageProjector" should {
      "properly project edited messages" in {
        editedMessageProjector.project(updateEditedTextMessage) shouldBe Some(editedTextMessageProjection)
      }

      "not match any other updates" in {
        (allUpdates - updateEditedTextMessage).flatMap(editedMessageProjector.project) shouldBe empty
      }
    }
  }
}
