package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.MultipartBody.*
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import io.github.aalbul.zio.telegram.domain.{BotCommand, BotCommandScope}

object SetMyCommands {

  /** Constructs minimal [[SetMyCommands]] command
    * @param commands
    *   A JSON-serialized list of bot commands to be set as the list of the bot's commands. At most 100 commands can be
    *   specified.
    * @return
    *   [[SetMyCommands]] builder
    */
  def of(commands: Seq[BotCommand]): SetMyCommands = SetMyCommands(
    commands = commands,
    scope = None,
    languageCode = None
  )
}

/** Use this method to change the list of the bot's commands. See
  * [[https://core.telegram.org/bots/features#commands this manual]] for more details about bot commands. Returns True
  * on success.
  */
case class SetMyCommands(commands: Seq[BotCommand], scope: Option[BotCommandScope], languageCode: Option[String])
  extends Command[Boolean] {

  override val name: String = "setMyCommands"
  override def parameters: ApiParameters = MultipartBody.ofOpt(
    Some(stringPart("commands", commands)),
    scope.map(stringPart("scope", _)),
    languageCode.map(stringPart("language_code", _))
  )

  /** A JSON-serialized object, describing scope of users for which the commands are relevant. Defaults to
    * [[https://core.telegram.org/bots/api#botcommandscopedefault BotCommandScopeDefault]].
    */
  def withScope(scope: BotCommandScope): SetMyCommands = copy(scope = Some(scope))

  /** A two-letter ISO 639-1 language code. If empty, commands will be applied to all users from the given scope, for
    * whose language there are no dedicated commands
    */
  def withLanguageCode(languageCode: String): SetMyCommands = copy(languageCode = Some(languageCode))
}
