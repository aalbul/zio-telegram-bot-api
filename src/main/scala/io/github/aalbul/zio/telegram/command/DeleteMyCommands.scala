package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import io.github.aalbul.zio.telegram.domain.BotCommandScope
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object DeleteMyCommands {

  /** Constructs minimal [[DeleteMyCommands]] command
    * @return
    *   [[DeleteMyCommands]] builder
    */
  def of(): DeleteMyCommands = DeleteMyCommands(scope = None, languageCode = None)
}

case class DeleteMyCommands(scope: Option[BotCommandScope], languageCode: Option[String]) extends Command[Boolean] {
  override val name: String = "deleteMyCommands"
  override def parameters: ApiParameters = MultipartBody.ofOpt(
    scope.map(stringPart("scope", _)),
    languageCode.map(stringPart("language_code", _))
  )

  /** A JSON-serialized object, describing scope of users for which the commands are relevant. Defaults to
    * [[https://core.telegram.org/bots/api#botcommandscopedefault BotCommandScopeDefault]].
    */
  def withScope(scope: BotCommandScope): DeleteMyCommands = copy(scope = Some(scope))

  /** A two-letter ISO 639-1 language code. If empty, commands will be applied to all users from the given scope, for
    * whose language there are no dedicated commands
    */
  def withLanguageCode(languageCode: String): DeleteMyCommands = copy(languageCode = Some(languageCode))
}
