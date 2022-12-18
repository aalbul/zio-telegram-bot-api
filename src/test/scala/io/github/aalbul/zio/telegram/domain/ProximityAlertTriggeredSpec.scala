package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class ProximityAlertTriggeredSpec extends BaseSpec {
  "ProximityAlertTriggered" when {
    "encoder" should {
      "encode pre checkout query to json" in {
        writeToString(proximityAlertTriggered1) should matchJsonResource("json/model/proximity-alert-triggered.json")
      }
    }

    "decoder" should {
      "decode proximity alert triggered from json" in {
        jsonResourceAs[ProximityAlertTriggered](
          "json/model/proximity-alert-triggered.json"
        ) shouldBe proximityAlertTriggered1
      }
    }
  }
}
