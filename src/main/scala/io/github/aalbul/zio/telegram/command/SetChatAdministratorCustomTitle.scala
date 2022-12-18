package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.SetChatAdministratorCustomTitle.SetChatAdministratorCustomTitlePayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import codecs.boolean

object SetChatAdministratorCustomTitle {
  object SetChatAdministratorCustomTitlePayload {
    implicit val setChatAdministratorCustomTitlePayloadJsonCodec
      : JsonValueCodec[SetChatAdministratorCustomTitlePayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class SetChatAdministratorCustomTitlePayload(chatId: String, userId: Long, customTitle: String)

  /** Constructs minimal [[SetChatAdministratorCustomTitle]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @param userId
    *   Unique identifier of the target user
    * @param customTitle
    *   New custom title for the administrator; 0-16 characters, emoji are not allowed
    * @return
    *   [[SetChatAdministratorCustomTitle]] builder
    */
  def of(chatId: String, userId: Long, customTitle: String): SetChatAdministratorCustomTitle =
    SetChatAdministratorCustomTitle(
      SetChatAdministratorCustomTitlePayload(chatId, userId, customTitle)
    )
}

/** Use this method to set a custom title for an administrator in a supergroup promoted by the bot. Returns ''True'' on
  * success.
  */
case class SetChatAdministratorCustomTitle(payload: SetChatAdministratorCustomTitlePayload) extends Command[Boolean] {
  override val name: String = "setChatAdministratorCustomTitle"

  override def parameters: ApiParameters = JsonBody(payload)
}
