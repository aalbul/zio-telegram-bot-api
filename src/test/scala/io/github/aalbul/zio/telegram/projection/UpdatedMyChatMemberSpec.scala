package io.github.aalbul.zio.telegram.projection

import io.github.aalbul.zio.telegram.test.BaseSpec
import UpdatedMyChatMember.*

class UpdatedMyChatMemberSpec extends BaseSpec {
  "UpdatedMyChatMember" when {
    "updatedMyChatMemberProjector" should {
      "properly handle new updated my chat member messages" in {
        updatedMyChatMemberProjector.project(updatedMyChatMemberMessage) shouldBe Some(updatedChatMemberProjection)
      }

      "not match any other updates" in {
        (allUpdates - updatedMyChatMemberMessage).flatMap(updatedMyChatMemberProjector.project) shouldBe empty
      }
    }
  }
}
