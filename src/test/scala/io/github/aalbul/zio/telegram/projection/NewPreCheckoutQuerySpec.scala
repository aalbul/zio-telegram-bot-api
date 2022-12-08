package io.github.aalbul.zio.telegram.projection

import io.github.aalbul.zio.telegram.test.BaseSpec
import NewPreCheckoutQuery.*

class NewPreCheckoutQuerySpec extends BaseSpec {
  "NewPreCheckoutQuery" when {
    "newPreCheckoutQueryProjector" should {
      "properly handle new pre checkout query messages" in {
        newPreCheckoutQueryProjector.project(preCheckoutQueryMessage) shouldBe Some(newPreCheckoutQueryProjection)
      }

      "not match any other updates" in {
        (allUpdates - preCheckoutQueryMessage).flatMap(newPreCheckoutQueryProjector.project) shouldBe empty
      }
    }
  }
}
