package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.test.BaseSpec
import VenueMessage.venueMessageProjector

class VenueMessageSpec extends BaseSpec {
  "VenueMessage" when {
    "venueMessageProjector" should {
      "properly project venue messages" in {
        venueMessageProjector.project(venueMessage1) shouldBe Some(venueMessageProjection)
      }

      "not match any other messages" in {
        (allMessages - venueMessage1).flatMap(venueMessageProjector.project) shouldBe empty
      }
    }
  }
}
