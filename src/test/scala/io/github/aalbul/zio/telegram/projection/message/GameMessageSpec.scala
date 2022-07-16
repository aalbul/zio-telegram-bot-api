package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.test.BaseSpec
import GameMessage.gameMessageProjector

class GameMessageSpec extends BaseSpec {
  "GameMessage" when {
    "gameMessageProjector" should {
      "properly project game messages" in {
        gameMessageProjector.project(gameMessage1) shouldBe Some(gameMessageProjection)
      }

      "not match any other messages" in {
        (allMessages - gameMessage1).flatMap(gameMessageProjector.project) shouldBe empty
      }
    }
  }
}
