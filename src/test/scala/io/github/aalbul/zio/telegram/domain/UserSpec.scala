package io.github.aalbul.zio.telegram.domain

import io.circe.syntax.EncoderOps
import io.github.aalbul.zio.telegram.test.BaseSpec

class UserSpec extends BaseSpec {
  "User" when {
    "encoder" should {
      "encode user to json" in {
        user1.asJson shouldBe jsonResource("json/model/user.json")
      }
    }

    "decoder" should {
      "decode user from json" in {
        jsonResourceAs[User]("json/model/user.json") shouldBe user1
      }
    }
  }
}
