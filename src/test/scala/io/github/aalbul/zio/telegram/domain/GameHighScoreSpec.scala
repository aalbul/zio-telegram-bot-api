package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class GameHighScoreSpec extends BaseSpec {
  "GameHighScore" when {
    "encoder" should {
      "encode game high score to json" in {
        writeToString(gameHighScore1) should matchJsonResource("json/model/game-high-score.json")
      }
    }

    "decoder" should {
      "decode game high score from json" in {
        jsonResourceAs[GameHighScore](
          "json/model/game-high-score.json"
        ) shouldBe gameHighScore1
      }
    }
  }
}
