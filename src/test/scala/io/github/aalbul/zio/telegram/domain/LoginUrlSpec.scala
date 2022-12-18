package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class LoginUrlSpec extends BaseSpec {
  "LoginUrl" when {
    "encoder" should {
      "encode login url to json" in {
        writeToString(loginUrl1) should matchJsonResource("json/model/login-url.json")
      }
    }

    "decoder" should {
      "decode login url from json" in {
        jsonResourceAs[LoginUrl]("json/model/login-url.json") shouldBe loginUrl1
      }
    }
  }
}
