package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object ChatLocation {
  implicit val chatLocationJsonCodec: JsonValueCodec[ChatLocation] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[ChatLocation]] object
    * @param location
    *   The location to which the supergroup is connected. Can't be a live location.
    * @param address
    *   Location address; 1-64 characters, as defined by the chat owner
    * @return
    *   [[ChatLocation]] builder
    */
  def of(location: Location, address: String): ChatLocation = ChatLocation(
    location = location,
    address = address
  )
}

/** Represents a location to which a chat is connected.
  */
case class ChatLocation(location: Location, address: String)
