package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.{readFromString, writeToString}
import io.github.aalbul.zio.telegram.test.BaseSpec

class DiceTypeSpec extends BaseSpec {
  "DiceType" when {
    "encoder" should {
      "properly encode dice type to json" in {
        writeToString[DiceType](DiceType.Die) shouldBe "\"🎲\""
        writeToString[DiceType](DiceType.DirectHit) shouldBe "\"🎯\""
        writeToString[DiceType](DiceType.Basketball) shouldBe "\"🏀\""
        writeToString[DiceType](DiceType.Football) shouldBe "\"⚽\""
        writeToString[DiceType](DiceType.Bowling) shouldBe "\"🎳\""
        writeToString[DiceType](DiceType.SlotMachine) shouldBe "\"🎰\""
      }
    }

    "decoder" should {
      "properly decode dice type from json" in {
        readFromString[DiceType]("\"🎲\"") shouldBe DiceType.Die
        readFromString[DiceType]("\"🎯\"") shouldBe DiceType.DirectHit
        readFromString[DiceType]("\"🏀\"") shouldBe DiceType.Basketball
        readFromString[DiceType]("\"⚽\"") shouldBe DiceType.Football
        readFromString[DiceType]("\"🎳\"") shouldBe DiceType.Bowling
        readFromString[DiceType]("\"🎰\"") shouldBe DiceType.SlotMachine
      }
    }
  }
}
