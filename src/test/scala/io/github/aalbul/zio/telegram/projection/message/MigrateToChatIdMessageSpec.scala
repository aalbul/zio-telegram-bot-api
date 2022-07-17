package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.test.BaseSpec
import MigrateToChatIdMessage.migrateToChatIdProjector

class MigrateToChatIdMessageSpec extends BaseSpec {
  "MigrateToChatIdMessage" when {
    "migrateToChatIdProjector" should {
      "properly project new migrate to chat id messages" in {
        migrateToChatIdProjector.project(migrateToChatIdMessage1) shouldBe Some(migrateToChatIdProjection)
      }

      "not match any other messages" in {
        (allMessages - migrateToChatIdMessage1).flatMap(migrateToChatIdProjector.project) shouldBe empty
      }
    }
  }
}
