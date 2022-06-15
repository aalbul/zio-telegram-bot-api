package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.test.BaseSpec
import DiceTypes.diceTypeEncoder
import io.circe.Json

class DiceTypesSpec extends BaseSpec {
  "DiceTypes" when {
    "diceTypeEncoder" should {
      "properly encode dice types" in {
        diceTypeEncoder(DiceTypes.Die) shouldBe Json.fromString("🎲")
        diceTypeEncoder(DiceTypes.DirectHit) shouldBe Json.fromString("🎯")
        diceTypeEncoder(DiceTypes.Basketball) shouldBe Json.fromString("🏀")
        diceTypeEncoder(DiceTypes.Football) shouldBe Json.fromString("⚽")
        diceTypeEncoder(DiceTypes.Bowling) shouldBe Json.fromString("🎳")
        diceTypeEncoder(DiceTypes.SlotMachine) shouldBe Json.fromString("🎰")
      }
    }
  }
}
