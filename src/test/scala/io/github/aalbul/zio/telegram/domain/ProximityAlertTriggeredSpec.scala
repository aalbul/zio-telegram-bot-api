package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class ProximityAlertTriggeredSpec extends BaseSpec {
  "ProximityAlertTriggered" when {
    "decoder" should {
      "decode proximity alert triggered json" in {
        jsonResourceAs[ProximityAlertTriggered](
          "json/model/proximity-alert-triggered.json"
        ) shouldBe proximityAlertTriggered1
      }
    }
  }
}
