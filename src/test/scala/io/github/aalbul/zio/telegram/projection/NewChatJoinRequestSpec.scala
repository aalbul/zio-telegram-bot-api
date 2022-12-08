package io.github.aalbul.zio.telegram.projection

import io.github.aalbul.zio.telegram.projection.NewCallbackQuery.newCallbackQueryProjector
import io.github.aalbul.zio.telegram.test.BaseSpec
import NewChatJoinRequest.*

class NewChatJoinRequestSpec extends BaseSpec {
  "NewChatJoinRequest" when {
    "newChatJoinRequestProjector" should {
      "properly handle new chat join request messages" in {
        newChatJoinRequestProjector.project(newChatJoinRequestMessage) shouldBe Some(newChatJoinRequestProjection)
      }

      "not match any other updates" in {
        (allUpdates - newChatJoinRequestMessage).flatMap(newChatJoinRequestProjector.project) shouldBe empty
      }
    }
  }
}
