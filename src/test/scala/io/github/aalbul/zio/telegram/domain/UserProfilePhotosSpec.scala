package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class UserProfilePhotosSpec extends BaseSpec {
  "UserProfilePhotos" when {
    "decoder" should {
      "decode user profile photos from json" in {
        jsonResourceAs[UserProfilePhotos]("json/model/user-profile-photos.json") shouldBe userProfilePhotos1
      }
    }
  }
}
