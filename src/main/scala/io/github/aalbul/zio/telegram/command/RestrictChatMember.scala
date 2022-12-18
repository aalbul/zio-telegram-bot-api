package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.RestrictChatMember.RestrictChatMemberPayload
import io.github.aalbul.zio.telegram.domain.ChatPermissions
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import codecs.boolean

import java.time.Instant

object RestrictChatMember {
  object RestrictChatMemberPayload {
    implicit val restrictChatMemberPayloadJsonCodec: JsonValueCodec[RestrictChatMemberPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class RestrictChatMemberPayload(
    chatId: String,
    userId: Long,
    permissions: ChatPermissions,
    untilDate: Option[Instant]
  )

  /** Constructs minimal [[RestrictChatMember]] command
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @param userId
    *   Unique identifier of the target user
    * @param permissions
    *   A JSON-serialized object for new user permissions
    * @return
    *   [[RestrictChatMember]] builder
    */
  def of(chatId: String, userId: Long, permissions: ChatPermissions): RestrictChatMember = RestrictChatMember(
    RestrictChatMemberPayload(chatId = chatId, userId = userId, permissions = permissions, untilDate = None)
  )
}

/** Use this method to restrict a user in a supergroup. The bot must be an administrator in the supergroup for this to
  * work and must have the appropriate administrator rights. Pass ''True'' for all permissions to lift restrictions from
  * a user. Returns ''True'' on success.
  */
case class RestrictChatMember(payload: RestrictChatMemberPayload) extends Command[Boolean] {
  override val name: String = "restrictChatMember"

  override def parameters: ApiParameters = JsonBody(payload)

  /** Date when restrictions will be lifted for the user, unix time. If user is restricted for more than 366 days or
    * less than 30 seconds from the current time, they are considered to be restricted forever
    */
  def withUntilDate(date: Instant): RestrictChatMember = copy(payload = payload.copy(untilDate = Some(date)))
}
