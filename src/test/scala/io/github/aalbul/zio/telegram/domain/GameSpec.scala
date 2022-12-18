package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class GameSpec extends BaseSpec {
  "Game" when {
    "encode" should {
      "encode game to json" in {
        writeToString(game1) should matchJsonResource("json/model/game.json")
      }
    }

    "decoder" should {
      "decode game from json" in {
        jsonResourceAs[Game]("json/model/game.json") shouldBe game1
      }
    }
  }
}
