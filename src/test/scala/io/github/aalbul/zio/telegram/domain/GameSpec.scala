package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class GameSpec extends BaseSpec {
  "Game" when {
    "decoder" should {
      "should decode game json" in {
        jsonResourceAs[Game]("json/model/game.json") shouldBe game1
      }
    }
  }
}
