package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class WebAppInfoSpec extends BaseSpec {
  "WebAppInfo" when {
    "encoder" should {
      "encode web app info to json" in {
        writeToString(webAppInfo1) should matchJsonResource("json/model/web-app-info.json")
      }
    }

    "decoder" should {
      "decode web app info from json" in {
        jsonResourceAs[WebAppInfo]("json/model/web-app-info.json") shouldBe webAppInfo1
      }
    }
  }
}
