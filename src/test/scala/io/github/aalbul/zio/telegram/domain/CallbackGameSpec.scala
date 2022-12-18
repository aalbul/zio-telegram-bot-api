package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class CallbackGameSpec extends BaseSpec {
  "CallbackGame" when {
    "encoder" should {
      "encode callback game to json" in {
        writeToString(callbackGame1) should matchJsonResource("json/model/callback-game.json")
      }
    }

    "decoder" should {
      "decode callback game from json" in {
        jsonResourceAs[CallbackGame]("json/model/callback-game.json") shouldBe callbackGame1
      }
    }
  }
}
