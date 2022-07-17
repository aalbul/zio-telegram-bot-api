package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.projection.message.ProximityAlertTriggeredMessage.proximityAlertTriggeredProjector
import io.github.aalbul.zio.telegram.test.BaseSpec

class ProximityAlertTriggeredMessageSpec extends BaseSpec {
  "ProximityAlertTriggeredMessage" when {
    "proximityAlertTriggeredProjector" should {
      "properly project proximity alert triggered messages" in {
        proximityAlertTriggeredProjector.project(proximityAlertTriggeredMessage1) shouldBe Some(
          proximityAlertTriggeredProjection
        )
      }

      "not match any other messages" in {
        (allMessages - proximityAlertTriggeredMessage1).flatMap(proximityAlertTriggeredProjector.project) shouldBe empty
      }
    }
  }
}
