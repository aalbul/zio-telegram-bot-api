package io.github.aalbul.zio.telegram.projection

import io.github.aalbul.zio.telegram.projection.NewShippingQuery.newShippingQueryProjector
import io.github.aalbul.zio.telegram.test.BaseSpec

class NewShippingQuerySpec extends BaseSpec {
  "NewShippingQuery" when {
    "newShippingQueryProjector" should {
      "properly handle new shipping query messages" in {
        newShippingQueryProjector.project(shippingQueryMessage) shouldBe Some(newShippingQueryProjection)
      }

      "not match any other updates" in {
        (allUpdates - shippingQueryMessage).flatMap(newShippingQueryProjector.project) shouldBe empty
      }
    }
  }
}
