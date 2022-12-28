package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import io.github.aalbul.zio.telegram.domain.{BotCommand, BotCommandScope}

object GetMyCommands {

  /** Constructs minimal [[GetMyCommands]] command
    * @return
    *   [[GetMyCommands]] builder
    */
  def of(): GetMyCommands = GetMyCommands(scope = None, languageCode = None)
}

/** Use this method to get the current list of the bot's commands for the given scope and user language. Returns an
  * Array of [[https://core.telegram.org/bots/api#botcommand BotCommand]] objects. If commands aren't set, an empty list
  * is returned.
  */
case class GetMyCommands(scope: Option[BotCommandScope], languageCode: Option[String])
  extends Command[Seq[BotCommand]] {
  override val name: String = "getMyCommands"
  override def parameters: ApiParameters = MultipartBody.ofOpt(
    scope.map(stringPart("scope", _)),
    languageCode.map(stringPart("language_code", _))
  )

  /** A JSON-serialized object, describing scope of users for which the commands are relevant. Defaults to
    * [[https://core.telegram.org/bots/api#botcommandscopedefault BotCommandScopeDefault]].
    */
  def withScope(scope: BotCommandScope): GetMyCommands = copy(scope = Some(scope))

  /** A two-letter ISO 639-1 language code. If empty, commands will be applied to all users from the given scope, for
    * whose language there are no dedicated commands
    */
  def withLanguageCode(languageCode: String): GetMyCommands = copy(languageCode = Some(languageCode))
}
