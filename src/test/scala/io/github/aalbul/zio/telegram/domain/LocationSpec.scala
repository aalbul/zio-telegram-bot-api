package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class LocationSpec extends BaseSpec {
  "Location" when {
    "encoder" should {
      "decode location to json" in {
        writeToString(location1) should matchJsonResource("json/model/location.json")
      }
    }

    "decoder" should {
      "decode location from json" in {
        jsonResourceAs[Location]("json/model/location.json") shouldBe location1
      }
    }
  }
}
