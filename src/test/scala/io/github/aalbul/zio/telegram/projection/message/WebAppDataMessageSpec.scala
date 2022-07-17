package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.test.BaseSpec
import WebAppDataMessage.webAppDataProjector

class WebAppDataMessageSpec extends BaseSpec {
  "WebAppDataMessage" when {
    "webAppDataProjector" should {
      "properly project web app data messages" in {
        webAppDataProjector.project(webAppDataMessage1) shouldBe Some(webAppDataProjection)
      }

      "not match any other messages" in {
        (allMessages - webAppDataMessage1).flatMap(webAppDataProjector.project) shouldBe empty
      }
    }
  }
}
