package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class WebAppInfoSpec extends BaseSpec {
  "WebAppInfo" when {
    "decoder" should {
      "decode web app info from json" in {
        jsonResourceAs[WebAppInfo]("json/model/web-app-info.json") shouldBe webAppInfo1
      }
    }
  }
}
