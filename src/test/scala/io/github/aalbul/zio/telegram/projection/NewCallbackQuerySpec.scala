package io.github.aalbul.zio.telegram.projection

import io.github.aalbul.zio.telegram.projection.NewCallbackQuery.newCallbackQueryProjector
import io.github.aalbul.zio.telegram.test.BaseSpec

class NewCallbackQuerySpec extends BaseSpec {
  "NewCallbackQuery" when {
    "newCallbackQueryProjector" should {
      "properly handle new callback query messages" in {
        newCallbackQueryProjector.project(callbackQueryMessage) shouldBe Some(newCallbackQueryProjection)
      }

      "not match any other updates" in {
        (allUpdates - callbackQueryMessage).flatMap(newCallbackQueryProjector.project) shouldBe empty
      }
    }
  }
}
