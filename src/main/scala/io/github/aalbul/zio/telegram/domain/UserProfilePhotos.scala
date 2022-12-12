package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

/** This object represent a user's profile pictures.
  */
object UserProfilePhotos {

  /** Constructs minimal [[UserProfilePhotos]]
    * @param totalCount
    *   Total number of profile pictures the target user has
    * @param photos
    *   Requested profile pictures (in up to 4 sizes each)
    * @return
    *   [[UserProfilePhotos]] builder
    */
  def of(totalCount: Long, photos: Seq[Seq[PhotoSize]]): UserProfilePhotos = UserProfilePhotos(
    totalCount = totalCount,
    photos = photos
  )
}

@ConfiguredJsonCodec(decodeOnly = true)
case class UserProfilePhotos(totalCount: Long, photos: Seq[Seq[PhotoSize]])
