package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object VideoChatStarted {

  /** Constructs minimal [[VideoChatStarted]]
    * @return
    *   [[VideoChatStarted]] builder
    */
  def of(): VideoChatStarted = VideoChatStarted()

  implicit val videoChatStartedJsonCodec: JsonValueCodec[VideoChatStarted] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
}

/** This object represents a service message about a video chat started in the chat. Currently holds no information.
  */
case class VideoChatStarted()
