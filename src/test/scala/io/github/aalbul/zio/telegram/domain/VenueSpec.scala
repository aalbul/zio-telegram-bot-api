package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class VenueSpec extends BaseSpec {
  "Venue" when {
    "encoder" should {
      "encode venue to json" in {
        writeToString(venue1) should matchJsonResource("json/model/venue.json")
      }
    }

    "decoder" should {
      "decode venue from json" in {
        jsonResourceAs[Venue]("json/model/venue.json") shouldBe venue1
      }
    }
  }
}
