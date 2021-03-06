package io.github.aalbul.zio.telegram.command

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.command.ExportChatInviteLink.ExportChatInviteLinkPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object ExportChatInviteLink {
  @ConfiguredJsonCodec(encodeOnly = true)
  case class ExportChatInviteLinkPayload(chatId: String)

  /** Constructs minimal [[ExportChatInviteLink]] command
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @return
    *   [[ExportChatInviteLink]] builder
    */
  def of(chatId: String): ExportChatInviteLink = ExportChatInviteLink(ExportChatInviteLinkPayload(chatId))
}

/** Use this method to generate a new primary invite link for a chat; any previously generated primary link is revoked.
  * The bot must be an administrator in the chat for this to work and must have the appropriate administrator rights.
  * Returns the new invite link as ''String'' on success.
  *
  * Note: Each administrator in a chat generates their own invite links. Bots can't use invite links generated by other
  * administrators. If you want your bot to work with invite links, it will need to generate its own link using
  * [[ExportChatInviteLink]] or by calling the [[GetChat]] method. If your bot needs to generate a new primary invite
  * link replacing its previous one, use [[ExportChatInviteLink]] again.
  */
case class ExportChatInviteLink(payload: ExportChatInviteLinkPayload) extends Command[String] {
  override val name: String = "exportChatInviteLink"

  override def parameters: ApiParameters = JsonBody(payload)
}
