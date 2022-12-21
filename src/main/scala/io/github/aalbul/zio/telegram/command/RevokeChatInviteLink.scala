package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.RevokeChatInviteLink.RevokeChatInviteLinkPayload
import io.github.aalbul.zio.telegram.domain.ChatInviteLink
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object RevokeChatInviteLink {
  object RevokeChatInviteLinkPayload {
    implicit val revokeChatInviteLinkPayloadJsonCodec: JsonValueCodec[RevokeChatInviteLinkPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class RevokeChatInviteLinkPayload(chatId: String, inviteLink: String)

  /** Constructs minimal [[RevokeChatInviteLink]] command
    *
    * @param chatId
    *   Unique identifier of the target chat or username of the target channel (in the format @channelusername)
    * @param inviteLink
    *   The invite link to revoke
    * @return
    *   [[RevokeChatInviteLink]] builder
    */
  def of(chatId: String, inviteLink: String): RevokeChatInviteLink = RevokeChatInviteLink(
    RevokeChatInviteLinkPayload(chatId = chatId, inviteLink = inviteLink)
  )
}

/** Use this method to revoke an invite link created by the bot. If the primary link is revoked, a new link is
  * automatically generated. The bot must be an administrator in the chat for this to work and must have the appropriate
  * administrator rights. Returns the revoked invite link as
  * [[https://core.telegram.org/bots/api#chatinvitelink ChatInviteLink]] object.
  */
case class RevokeChatInviteLink(payload: RevokeChatInviteLinkPayload) extends Command[ChatInviteLink] {
  override val name: String = "revokeChatInviteLink"

  override def parameters: ApiParameters = JsonBody(payload)
}
