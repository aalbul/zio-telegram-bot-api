package io.github.aalbul.zio.telegram.command

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.command.GetUserProfilePhotos.GetUserProfilePhotosPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import io.github.aalbul.zio.telegram.domain.UserProfilePhotos

object GetUserProfilePhotos {
  @ConfiguredJsonCodec(encodeOnly = true)
  case class GetUserProfilePhotosPayload(userId: Long, offset: Option[Long], limit: Option[Long])

  /** Constructs minimal [[GetUserProfilePhotos]] command
    * @param userId
    *   Unique identifier of the target user
    * @return
    *   [[GetUserProfilePhotos]] builder
    */
  def of(userId: Long): GetUserProfilePhotos = GetUserProfilePhotos(
    GetUserProfilePhotosPayload(
      userId = userId,
      offset = None,
      limit = None
    )
  )
}

/** Use this method to get a list of profile pictures for a user. Returns a [[UserProfilePhotos]] object. */
case class GetUserProfilePhotos(payload: GetUserProfilePhotosPayload) extends Command[UserProfilePhotos] {
  override val name: String = "getUserProfilePhotos"

  override def parameters: ApiParameters = JsonBody(payload)

  /** Sequential number of the first photo to be returned. By default, all photos are returned.
    */
  def withOffset(offset: Long): GetUserProfilePhotos = copy(payload = payload.copy(offset = Some(offset)))

  /** Limits the number of photos to be retrieved. Values between 1-100 are accepted. Defaults to 100.
    */
  def withLimit(limit: Long): GetUserProfilePhotos = copy(payload = payload.copy(limit = Some(limit)))
}
