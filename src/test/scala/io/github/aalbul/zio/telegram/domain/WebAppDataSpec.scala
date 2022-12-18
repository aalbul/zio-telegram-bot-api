package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class WebAppDataSpec extends BaseSpec {
  "WebAppData" when {
    "encoder" should {
      "encode web app data to json" in {
        writeToString(webAppData1) should matchJsonResource("json/model/web-app-data.json")
      }
    }

    "decoder" should {
      "decode web app data from json" in {
        jsonResourceAs[WebAppData]("json/model/web-app-data.json") shouldBe webAppData1
      }
    }
  }
}
