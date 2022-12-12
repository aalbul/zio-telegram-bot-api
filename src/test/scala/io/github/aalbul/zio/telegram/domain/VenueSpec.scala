package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class VenueSpec extends BaseSpec {
  "Venue" when {
    "decoder" should {
      "decode venue from json" in {
        jsonResourceAs[Venue]("json/model/venue.json") shouldBe venue1
      }
    }
  }
}
