package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.GetUserProfilePhotos.GetUserProfilePhotosPayload
import io.github.aalbul.zio.telegram.domain.UserProfilePhotos
import io.github.aalbul.zio.telegram.test.BaseSpec

class GetUserProfilePhotosSpec extends BaseSpec {
  trait Scope {
    val command: Command[UserProfilePhotos] =
      GetUserProfilePhotos
        .of(661)
        .withOffset(2)
        .withLimit(50)

    val payload: GetUserProfilePhotosPayload = GetUserProfilePhotosPayload(
      userId = 661,
      offset = Some(2),
      limit = Some(50)
    )
  }

  "GetUserProfilePhotos" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "getUserProfilePhotos"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "GetUserProfilePhotosPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/get-user-profile-photos-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[GetUserProfilePhotosPayload](
            "json/command/get-user-profile-photos-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
