package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class LocationSpec extends BaseSpec {
  "Location" when {
    "decoder" should {
      "should decode location json" in {
        jsonResourceAs[Location]("json/model/location.json") shouldBe location1
      }
    }
  }
}
