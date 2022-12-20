package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object PollAnswer {
  implicit val pollAnswerJsonCodec: JsonValueCodec[PollAnswer] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[PollAnswer]]
    * @param pollId
    *   Unique poll identifier
    * @param user
    *   The user, who changed the answer to the poll
    * @param optionIds
    *   0-based identifiers of answer options, chosen by the user. May be empty if the user retracted their vote.
    * @return
    *   [[PollAnswer]] builder
    */
  def of(pollId: String, user: User, optionIds: Seq[Int]): PollAnswer = PollAnswer(
    pollId = pollId,
    user = user,
    optionIds = optionIds
  )
}

/** This object represents an answer of a user in a non-anonymous poll.
  */
case class PollAnswer(pollId: String, user: User, optionIds: Seq[Int])
