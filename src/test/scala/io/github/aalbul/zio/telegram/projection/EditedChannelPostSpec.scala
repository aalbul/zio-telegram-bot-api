package io.github.aalbul.zio.telegram.projection

import io.github.aalbul.zio.telegram.projection.EditedChannelPost.editedChannelPostProjector
import io.github.aalbul.zio.telegram.test.BaseSpec

class EditedChannelPostSpec extends BaseSpec {
  "EditedChannelPost" when {
    "editedChannelPostProjector" should {
      "properly project edited channel post messages" in {
        editedChannelPostProjector.project(editedChannelPostMessage) shouldBe Some(editedChannelPostProjection)
      }

      "not match any other updates" in {
        (allUpdates - editedChannelPostMessage).flatMap(editedChannelPostProjector.project) shouldBe empty
      }
    }
  }
}
