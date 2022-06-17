package io.github.aalbul.zio.telegram.command

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.command.GetUserProfilePhotos.GetUserProfilePhotosPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import io.github.aalbul.zio.telegram.domain.UserProfilePhotos

object GetUserProfilePhotos {
  @ConfiguredJsonCodec(encodeOnly = true)
  case class GetUserProfilePhotosPayload(userId: String, offset: Option[Long], limit: Option[Long])

  def of(userId: String): GetUserProfilePhotos = GetUserProfilePhotos(
    GetUserProfilePhotosPayload(
      userId = userId,
      offset = None,
      limit = None
    )
  )
}

case class GetUserProfilePhotos(payload: GetUserProfilePhotosPayload) extends Command[UserProfilePhotos] {
  override val name: String = "getUserProfilePhotos"

  override def parameters: ApiParameters = JsonBody(payload)

  def withOffset(offset: Long): GetUserProfilePhotos = copy(payload = payload.copy(offset = Some(offset)))
  def withLimit(limit: Long): GetUserProfilePhotos = copy(payload = payload.copy(limit = Some(limit)))
}
