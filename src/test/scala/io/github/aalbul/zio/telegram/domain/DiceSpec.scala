package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class DiceSpec extends BaseSpec {
  "Dice" when {
    "decoder" should {
      "should decode dice json" in {
        jsonResourceAs[Dice]("json/model/dice.json") shouldBe dice1
      }
    }
  }
}
