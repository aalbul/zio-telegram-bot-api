package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class SentWebAppMessageSpec extends BaseSpec {
  "SentWebAppMessage" when {
    "encoder" should {
      "encode reply sent web app message to json" in {
        writeToString(sentWebAppMessage1) should matchJsonResource("json/model/sent-web-app-message.json")
      }
    }

    "decoder" should {
      "decode reply sent web app message from json" in {
        jsonResourceAs[SentWebAppMessage](
          "json/model/sent-web-app-message.json"
        ) shouldBe sentWebAppMessage1
      }
    }
  }
}
