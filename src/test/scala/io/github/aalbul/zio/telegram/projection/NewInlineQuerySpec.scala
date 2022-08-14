package io.github.aalbul.zio.telegram.projection

import io.github.aalbul.zio.telegram.test.BaseSpec
import NewInlineQuery.newInlineQueryProjector

class NewInlineQuerySpec extends BaseSpec {
  "NewInlineQuery" when {
    "newInlineQueryProjector" should {
      "properly inline query messages" in {
        newInlineQueryProjector.project(inlineQueryMessage) shouldBe Some(inlineQueryProjection)
      }

      "not match any other updates" in {
        (allUpdates - inlineQueryMessage).flatMap(newInlineQueryProjector.project) shouldBe empty
      }
    }
  }
}
