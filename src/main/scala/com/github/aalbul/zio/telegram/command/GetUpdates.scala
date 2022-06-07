package com.github.aalbul.zio.telegram.command

import com.github.aalbul.zio.telegram.command.GetUpdates.GetUpdatesRequest
import com.github.aalbul.zio.telegram.domain.Update
import io.circe.generic.extras.ConfiguredJsonCodec

import java.time.Duration

object GetUpdates {
  @ConfiguredJsonCodec(encodeOnly = true)
  case class GetUpdatesRequest(
    offset: Option[Long],
    limit: Option[Long],
    timeout: Option[Long],
    allowedUpdates: Option[Set[String]]
  )

  def apply(): GetUpdates = GetUpdates(
    GetUpdatesRequest(
      offset = None,
      limit = None,
      timeout = None,
      allowedUpdates = None
    )
  )
}

case class GetUpdates(request: GetUpdatesRequest) extends Command[Seq[Update]] {
  override val name: String = "getUpdates"
  override def parameters: ApiParameters = JsonBody(request)

  def withOffset(offset: Long): GetUpdates = copy(request = request.copy(offset = Some(offset)))
  def withLimit(limit: Long): GetUpdates = copy(request = request.copy(limit = Some(limit)))
  def withTimeout(duration: Duration): GetUpdates = copy(request = request.copy(timeout = Some(duration.toSeconds)))
  def withAllowedUpdates(updateTypes: Set[String]): GetUpdates =
    copy(request = request.copy(allowedUpdates = Some(updateTypes)))
}
