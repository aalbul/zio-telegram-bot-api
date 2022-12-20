package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object PollOption {
  implicit val pollOptionJsonCodec: JsonValueCodec[PollOption] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[PollOption]]
    * @param text
    *   Option text, 1-100 characters
    * @param voterCount
    *   Number of users that voted for this option
    * @return
    *   [[PollOption]] builder
    */
  def of(text: String, voterCount: Long): PollOption = PollOption(text = text, voterCount = voterCount)
}

/** This object contains information about one answer option in a poll.
  */
case class PollOption(text: String, voterCount: Long)
