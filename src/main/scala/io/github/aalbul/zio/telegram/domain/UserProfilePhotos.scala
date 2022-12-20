package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

/** This object represent a user's profile pictures.
  */
object UserProfilePhotos {
  implicit val userProfilePhotosJsonCodec: JsonValueCodec[UserProfilePhotos] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

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

case class UserProfilePhotos(totalCount: Long, photos: Seq[Seq[PhotoSize]])
