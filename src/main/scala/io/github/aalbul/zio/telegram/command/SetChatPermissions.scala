package io.github.aalbul.zio.telegram.command

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.command.SetChatPermissions.SetChatPermissionsPayload
import io.github.aalbul.zio.telegram.domain.ChatPermissions
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object SetChatPermissions {
  @ConfiguredJsonCodec(encodeOnly = true)
  case class SetChatPermissionsPayload(chatId: String, permissions: ChatPermissions)

  /** Constructs minimal [[SetChatPermissions]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @param permissions
    *   new default [[ChatPermissions]]
    * @return
    *   [[SetChatPermissions]] builder
    */
  def of(chatId: String, permissions: ChatPermissions): SetChatPermissions = SetChatPermissions(
    SetChatPermissionsPayload(chatId = chatId, permissions = permissions)
  )
}

/** Use this method to set default chat permissions for all members. The bot must be an administrator in the group or a
  * supergroup for this to work and must have the ''can_restrict_members'' administrator rights. Returns ''True'' on
  * success.
  */
case class SetChatPermissions(payload: SetChatPermissionsPayload) extends Command[Boolean] {
  override val name: String = "setChatPermissions"

  override def parameters: ApiParameters = JsonBody(payload)
}
