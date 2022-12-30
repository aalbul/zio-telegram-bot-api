package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}

object GameHighScore {
  implicit val gameHighScoreJsonCodec: JsonValueCodec[GameHighScore] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  implicit val gameHighScoreSeqJsonCodec: JsonValueCodec[Seq[GameHighScore]] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[GameHighScore]]
    * @param position
    *   Position in high score table for the game
    * @param user
    *   User
    * @param score
    *   Score
    * @return
    *   [[GameHighScore]] builder
    */
  def of(position: Long, user: User, score: Long): GameHighScore = GameHighScore(
    position = position,
    user = user,
    score = score
  )
}

/** This object represents one row of the high scores table for a game.
  */
case class GameHighScore(position: Long, user: User, score: Long)
