package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.GetMyCommands.GetMyCommandsPayload
import io.github.aalbul.zio.telegram.domain.{BotCommand, BotCommandScope}

object GetMyCommands {

  object GetMyCommandsPayload {
    implicit val getMyCommandsPayloadJsonCodec: JsonValueCodec[GetMyCommandsPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class GetMyCommandsPayload(scope: Option[BotCommandScope], languageCode: Option[String])

  /** Constructs minimal [[GetMyCommands]] command
    * @return
    *   [[GetMyCommands]] builder
    */
  def of: GetMyCommands = GetMyCommands(GetMyCommandsPayload(scope = None, languageCode = None))
}

/** Use this method to get the current list of the bot's commands for the given scope and user language. Returns an
  * Array of [[https://core.telegram.org/bots/api#botcommand BotCommand]] objects. If commands aren't set, an empty list
  * is returned.
  */
case class GetMyCommands(payload: GetMyCommandsPayload) extends Command[Seq[BotCommand]] {
  override val name: String = "getMyCommands"
  override def parameters: ApiParameters = JsonBody(payload)

  /** A JSON-serialized object, describing scope of users for which the commands are relevant. Defaults to
    * [[https://core.telegram.org/bots/api#botcommandscopedefault BotCommandScopeDefault]].
    */
  def withScope(scope: BotCommandScope): GetMyCommands = copy(payload.copy(scope = Some(scope)))

  /** A two-letter ISO 639-1 language code. If empty, commands will be applied to all users from the given scope, for
    * whose language there are no dedicated commands
    */
  def withLanguageCode(languageCode: String): GetMyCommands = copy(payload.copy(languageCode = Some(languageCode)))
}
