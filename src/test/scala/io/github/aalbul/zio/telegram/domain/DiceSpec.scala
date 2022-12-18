package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class DiceSpec extends BaseSpec {
  "Dice" when {
    "encode" should {
      "encode dice to json" in {
        writeToString(dice1) should matchJsonResource("json/model/dice.json")
      }
    }

    "decoder" should {
      "decode dice from json" in {
        jsonResourceAs[Dice]("json/model/dice.json") shouldBe dice1
      }
    }
  }
}
