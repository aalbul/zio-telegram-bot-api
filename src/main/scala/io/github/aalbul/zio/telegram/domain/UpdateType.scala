package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import enumeratum.{EnumEntry, *}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

sealed trait UpdateType extends EnumEntry

object UpdateType extends Enum[UpdateType] {
  case object Message extends UpdateType
  case object EditedMessage extends UpdateType
  case object ChannelPost extends UpdateType
  case object EditedChannelPost extends UpdateType
  case object InlineQuery extends UpdateType
  case object ChosenInlineResult extends UpdateType
  case object CallbackQuery extends UpdateType
  case object ShippingQuery extends UpdateType
  case object PreCheckoutQuery extends UpdateType
  case object Poll extends UpdateType
  case object PollAnswer extends UpdateType
  case object MyChatMember extends UpdateType
  case object ChatMember extends UpdateType
  case object ChatJoinRequest extends UpdateType

  override def values: IndexedSeq[UpdateType] = findValues

  implicit val updateTypeJsonEncoder: JsonValueCodec[UpdateType] = makeEnumCodec(values)
  implicit val updateTypeSetJsonEncoder: JsonValueCodec[Set[UpdateType]] =
    JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
}
