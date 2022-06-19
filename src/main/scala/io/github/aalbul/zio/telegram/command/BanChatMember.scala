package io.github.aalbul.zio.telegram.command

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.command.BanChatMember.BanChatMemberPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

import java.time.Instant

object BanChatMember {
  @ConfiguredJsonCodec(encodeOnly = true)
  case class BanChatMemberPayload(
    chatId: String,
    userId: String,
    untilDate: Option[Instant],
    revokeMessages: Option[Boolean]
  )

  def of(chatId: String, userId: String): BanChatMember = BanChatMember(
    BanChatMemberPayload(chatId = chatId, userId = userId, untilDate = None, revokeMessages = None)
  )
}

case class BanChatMember(payload: BanChatMemberPayload) extends Command[Boolean] {
  override val name: String = "banChatMember"

  override def parameters: ApiParameters = JsonBody(payload)
  def withUntilDate(date: Instant): BanChatMember = copy(payload = payload.copy(untilDate = Some(date)))
  def withRevokeMessages(revoke: Boolean): BanChatMember = copy(payload = payload.copy(revokeMessages = Some(revoke)))
}
