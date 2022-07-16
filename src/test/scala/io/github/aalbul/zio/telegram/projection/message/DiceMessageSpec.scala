package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.test.BaseSpec
import DiceMessage.diceMessageProjector

class DiceMessageSpec extends BaseSpec {
  "DiceMessage" when {
    "diceMessageProjector" should {
      "properly project dice messages" in {
        diceMessageProjector.project(diceMessage1) shouldBe Some(diceMessageProjection)
      }

      "not match any other messages" in {
        (allMessages - diceMessage1).flatMap(diceMessageProjector.project) shouldBe empty
      }
    }
  }
}
