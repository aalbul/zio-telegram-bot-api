package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.projection.message.WriteAccessAllowedMessage.writeAccessAllowedMessageProjector
import io.github.aalbul.zio.telegram.test.BaseSpec

class WriteAccessAllowedMessageSpec extends BaseSpec {
  "WriteAccessAllowedMessage" when {
    "writeAccessAllowedMessageProjector" should {
      "properly project write access allowed projector messages" in {
        writeAccessAllowedMessageProjector.project(writeAccessAllowedMessage1) shouldBe Some(
          writeAccessAllowedMessageProjection
        )
      }

      "not match any other messages" in {
        (allMessages - writeAccessAllowedMessage1).flatMap(
          writeAccessAllowedMessageProjector.project
        ) shouldBe empty
      }
    }
  }
}
