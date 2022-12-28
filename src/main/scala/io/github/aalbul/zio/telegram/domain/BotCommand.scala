package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}

object BotCommand {
  implicit val botCommandJsonCodec: JsonValueCodec[BotCommand] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  implicit val botCommandSecJsonCodec: JsonValueCodec[Seq[BotCommand]] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[BotCommand]] object
    * @param command
    *   Text of the command; 1-32 characters. Can contain only lowercase English letters, digits and underscores.
    * @param description
    *   Description of the command; 1-256 characters
    * @return
    *   [[BotCommand]] builder
    */
  def of(command: String, description: String): BotCommand = BotCommand(command = command, description = description)
}

/** This object represents a bot command.
  */
case class BotCommand(command: String, description: String)
