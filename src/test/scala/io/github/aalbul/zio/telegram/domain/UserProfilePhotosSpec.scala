package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class UserProfilePhotosSpec extends BaseSpec {
  "UserProfilePhotos" when {
    "encoder" should {
      "encode user profile photos to json" in {
        writeToString(userProfilePhotos1) should matchJsonResource("json/model/user-profile-photos.json")
      }
    }

    "decoder" should {
      "decode user profile photos from json" in {
        jsonResourceAs[UserProfilePhotos]("json/model/user-profile-photos.json") shouldBe userProfilePhotos1
      }
    }
  }
}
