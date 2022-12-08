package io.github.aalbul.zio.telegram.projection

import io.github.aalbul.zio.telegram.test.BaseSpec
import UpdatedChatMember.*

class UpdatedChatMemberSpec extends BaseSpec {
  "UpdatedChatMember" when {
    "updatedChatMemberProjector" should {
      "properly handle new updated chat member messages" in {
        updatedChatMemberProjector.project(updatedChatMemberMessage) shouldBe Some(updatedChatMemberProjection)
      }

      "not match any other updates" in {
        (allUpdates - updatedChatMemberMessage).flatMap(updatedChatMemberProjector.project) shouldBe empty
      }
    }
  }
}
