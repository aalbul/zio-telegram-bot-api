package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class CallbackQuerySpec extends BaseSpec {
  "CallbackQuery" when {
    "decoder" should {
      "decode callback query json" in {
        jsonResourceAs[CallbackQuery]("json/model/callback-query.json") shouldBe callbackQuery1
      }
    }
  }
}
