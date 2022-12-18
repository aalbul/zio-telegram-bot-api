package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class CallbackQuerySpec extends BaseSpec {
  "CallbackQuery" when {
    "encoder" should {
      "encode callback query to json" in {
        writeToString(callbackQuery1) should matchJsonResource("json/model/callback-query.json")
      }
    }

    "decoder" should {
      "decode callback query from json" in {
        jsonResourceAs[CallbackQuery]("json/model/callback-query.json") shouldBe callbackQuery1
      }
    }
  }
}
