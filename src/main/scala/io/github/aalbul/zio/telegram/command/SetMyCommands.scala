package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.SetMyCommands.SetMyCommandsPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import io.github.aalbul.zio.telegram.domain.{BotCommand, BotCommandScope}

object SetMyCommands {

  object SetMyCommandsPayload {
    implicit val setMyCommandsPayloadJsonCodec: JsonValueCodec[SetMyCommandsPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class SetMyCommandsPayload(
    commands: Seq[BotCommand],
    scope: Option[BotCommandScope],
    languageCode: Option[String]
  )

  /** Constructs minimal [[SetMyCommands]] command
    * @param commands
    *   A JSON-serialized list of bot commands to be set as the list of the bot's commands. At most 100 commands can be
    *   specified.
    * @return
    *   [[SetMyCommands]] builder
    */
  def of(commands: Seq[BotCommand]): SetMyCommands = SetMyCommands(
    SetMyCommandsPayload(
      commands = commands,
      scope = None,
      languageCode = None
    )
  )
}

/** Use this method to change the list of the bot's commands. See
  * [[https://core.telegram.org/bots/features#commands this manual]] for more details about bot commands. Returns True
  * on success.
  */
case class SetMyCommands(payload: SetMyCommandsPayload) extends Command[Boolean] {
  override val name: String = "setMyCommands"
  override def parameters: ApiParameters = JsonBody(payload)

  /** A JSON-serialized object, describing scope of users for which the commands are relevant. Defaults to
    * [[https://core.telegram.org/bots/api#botcommandscopedefault BotCommandScopeDefault]].
    */
  def withScope(scope: BotCommandScope): SetMyCommands = copy(payload.copy(scope = Some(scope)))

  /** A two-letter ISO 639-1 language code. If empty, commands will be applied to all users from the given scope, for
    * whose language there are no dedicated commands
    */
  def withLanguageCode(languageCode: String): SetMyCommands = copy(payload.copy(languageCode = Some(languageCode)))
}
