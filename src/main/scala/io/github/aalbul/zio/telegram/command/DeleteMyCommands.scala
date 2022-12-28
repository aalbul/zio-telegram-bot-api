package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.DeleteMyCommands.DeleteMyCommandsPayload
import io.github.aalbul.zio.telegram.domain.BotCommandScope
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object DeleteMyCommands {

  object DeleteMyCommandsPayload {
    implicit val deleteMyCommandsPayloadJsonCodec: JsonValueCodec[DeleteMyCommandsPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class DeleteMyCommandsPayload(scope: Option[BotCommandScope], languageCode: Option[String])

  /** Constructs minimal [[DeleteMyCommands]] command
    * @return
    *   [[DeleteMyCommands]] builder
    */
  def of(): DeleteMyCommands = DeleteMyCommands(DeleteMyCommandsPayload(scope = None, languageCode = None))
}

case class DeleteMyCommands(payload: DeleteMyCommandsPayload) extends Command[Boolean] {
  override val name: String = "deleteMyCommands"
  override def parameters: ApiParameters = JsonBody(payload)

  /** A JSON-serialized object, describing scope of users for which the commands are relevant. Defaults to
    * [[https://core.telegram.org/bots/api#botcommandscopedefault BotCommandScopeDefault]].
    */
  def withScope(scope: BotCommandScope): DeleteMyCommands = copy(payload.copy(scope = Some(scope)))

  /** A two-letter ISO 639-1 language code. If empty, commands will be applied to all users from the given scope, for
    * whose language there are no dedicated commands
    */
  def withLanguageCode(languageCode: String): DeleteMyCommands = copy(payload.copy(languageCode = Some(languageCode)))
}
