package io.github.aalbul.zio.telegram.projection

import io.github.aalbul.zio.telegram.test.BaseSpec
import ChosenInlineResult.chosenInlineResultProjector

class ChosenInlineResultSpec extends BaseSpec {
  "ChosenInlineResult" when {
    "chosenInlineResultProjector" should {
      "properly project chosen inline result messages" in {
        chosenInlineResultProjector.project(chosenInlineResultMessage) shouldBe Some(chosenInlineResultProjection)
      }

      "not match any other updates" in {
        (allUpdates - chosenInlineResultMessage).flatMap(chosenInlineResultProjector.project) shouldBe empty
      }
    }
  }
}
