package io.github.aalbul.zio.telegram.domain

import io.circe.syntax.EncoderOps
import io.github.aalbul.zio.telegram.test.BaseSpec

class LoginUrlSpec extends BaseSpec {
  "LoginUrl" when {
    "encoder" should {
      "encode keyboard button to json" in {
        loginUrl1.asJson shouldBe jsonResource("json/model/login-url.json")
      }
    }

    "decoder" should {
      "decode login url json" in {
        jsonResourceAs[LoginUrl]("json/model/login-url.json") shouldBe loginUrl1
      }
    }
  }
}
