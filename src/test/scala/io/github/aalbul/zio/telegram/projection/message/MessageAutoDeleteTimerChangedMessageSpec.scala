package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.test.BaseSpec
import MessageAutoDeleteTimerChangedMessage.messageAutoDeleteTimerChangedProjector

class MessageAutoDeleteTimerChangedMessageSpec extends BaseSpec {
  "MessageAutoDeleteTimerChangedMessage" when {
    "messageAutoDeleteTimerChangedProjector" should {
      "properly project new message auto delete timer changed messages" in {
        messageAutoDeleteTimerChangedProjector.project(messageAutoDeleteTimerChangedMessage1) shouldBe Some(
          messageAutoDeleteTimerChangedProjection
        )
      }

      "not match any other messages" in {
        (allMessages - messageAutoDeleteTimerChangedMessage1).flatMap(
          messageAutoDeleteTimerChangedProjector.project
        ) shouldBe empty
      }
    }
  }
}
