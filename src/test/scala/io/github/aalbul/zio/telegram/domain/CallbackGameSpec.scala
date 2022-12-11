package io.github.aalbul.zio.telegram.domain

import io.circe.syntax.EncoderOps
import io.github.aalbul.zio.telegram.test.BaseSpec

class CallbackGameSpec extends BaseSpec {
  "CallbackGame" when {
    "encoder" should {
      "encode callback game" in {
        callbackGame1.asJson shouldBe jsonResource("json/model/callback-game.json")
      }
    }

    "decoder" should {
      "decode callback game" in {
        jsonResourceAs[CallbackGame]("json/model/callback-game.json") shouldBe callbackGame1
      }
    }
  }
}
