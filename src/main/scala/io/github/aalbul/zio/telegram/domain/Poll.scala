package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object Poll {
  implicit val pollJsonCodec: JsonValueCodec[Poll] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
}

case class Poll(
  id: String,
  question: String,
  options: Seq[PollOption],
  totalVoterCount: Int,
  isClosed: Boolean,
  isAnonymous: Boolean,
  `type`: PollType,
  allowsMultipleAnswers: Boolean,
  correctOptionId: Option[Int],
  explanation: Option[String],
  explanationEntities: Option[Seq[MessageEntity]],
  openPeriod: Option[Int],
  closeDate: Option[Int]
)
