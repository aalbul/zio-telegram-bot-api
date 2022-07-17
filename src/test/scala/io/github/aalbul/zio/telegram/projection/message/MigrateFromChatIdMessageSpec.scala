package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.projection.message.MigrateFromChatIdMessage.migrateFromChatIdProjector
import io.github.aalbul.zio.telegram.test.BaseSpec

class MigrateFromChatIdMessageSpec extends BaseSpec {
  "MigrateFromChatIdMessage" when {
    "migrateFromChatIdProjector" should {
      "properly project new migrate from chat id messages" in {
        migrateFromChatIdProjector.project(migrateFromChatIdMessage1) shouldBe Some(migrateFromChatIdProjection)
      }

      "not match any other messages" in {
        (allMessages - migrateFromChatIdMessage1).flatMap(migrateFromChatIdProjector.project) shouldBe empty
      }
    }
  }
}
