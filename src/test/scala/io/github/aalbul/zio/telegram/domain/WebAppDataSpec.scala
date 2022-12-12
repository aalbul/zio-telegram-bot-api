package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class WebAppDataSpec extends BaseSpec {
  "WebAppData" when {
    "decoder" should {
      "decode web app data from json" in {
        jsonResourceAs[WebAppData]("json/model/web-app-data.json") shouldBe webAppData1
      }
    }
  }
}
