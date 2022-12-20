package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object InlineQuery {
  implicit val inlineQueryJsonCodec: JsonValueCodec[InlineQuery] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[InlineQuery]]
    * @param id
    *   Unique identifier for this query
    * @param from
    *   Sender
    * @param query
    *   Text of the query (up to 256 characters)
    * @param offset
    *   Offset of the results to be returned, can be controlled by the bot
    * @return
    *   [[InlineQuery]] builder
    */
  def of(id: String, from: User, query: String, offset: String): InlineQuery = InlineQuery(
    id = id,
    from = from,
    query = query,
    offset = offset,
    chatType = None,
    location = None
  )
}

/** This object represents an incoming inline query. When the user sends an empty query, your bot could return some
  * default or trending results.
  */
case class InlineQuery(
  id: String,
  from: User,
  query: String,
  offset: String,
  chatType: Option[ChatType],
  location: Option[Location]
) {

  /** Type of the chat from which the inline query was sent. Can be either “sender” for a private chat with the inline
    * query sender, “private”, “group”, “supergroup”, or “channel”. The chat type should be always known for requests
    * sent from official clients and most third-party clients, unless the request was sent from a secret chat
    */
  def withChatType(chatType: ChatType): InlineQuery = copy(chatType = Some(chatType))

  /** Sender location, only for bots that request user location
    */
  def withLocation(location: Location): InlineQuery = copy(location = Some(location))
}
