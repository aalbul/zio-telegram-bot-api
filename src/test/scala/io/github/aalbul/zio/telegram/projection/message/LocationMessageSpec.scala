package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.test.BaseSpec
import LocationMessage.locationMessageProjector

class LocationMessageSpec extends BaseSpec {
  "LocationMessage" when {
    "locationMessageProjector" should {
      "properly project location messages" in {
        locationMessageProjector.project(locationMessage1) shouldBe Some(locationMessageProjection)
      }

      "not match any other messages" in {
        (allMessages - locationMessage1).flatMap(locationMessageProjector.project) shouldBe empty
      }
    }
  }
}
