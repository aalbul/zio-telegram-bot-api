package io.github.aalbul.zio.telegram.command

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.command.GetUpdates.GetUpdatesPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import io.github.aalbul.zio.telegram.domain.Update
import io.github.aalbul.zio.telegram.domain.UpdateTypes.UpdateType

import java.time.Duration
import java.time.temporal.ChronoUnit.SECONDS

object GetUpdates {
  @ConfiguredJsonCodec(encodeOnly = true)
  case class GetUpdatesPayload(
    offset: Option[Long],
    limit: Option[Long],
    timeout: Option[Long],
    allowedUpdates: Option[Set[UpdateType]]
  )

  def of: GetUpdates = GetUpdates(
    GetUpdatesPayload(
      offset = None,
      limit = None,
      timeout = None,
      allowedUpdates = None
    )
  )
}

case class GetUpdates(payload: GetUpdatesPayload) extends Command[Seq[Update]] {
  override val name: String = "getUpdates"
  override def parameters: ApiParameters = JsonBody(payload)

  def withOffset(offset: Long): GetUpdates = copy(payload = payload.copy(offset = Some(offset)))
  def withLimit(limit: Long): GetUpdates = copy(payload = payload.copy(limit = Some(limit)))
  def withTimeout(duration: Duration): GetUpdates = copy(payload = payload.copy(timeout = Some(duration.get(SECONDS))))
  def withAllowedUpdates(updateTypes: Set[UpdateType]): GetUpdates =
    copy(payload = payload.copy(allowedUpdates = Some(updateTypes)))
}
