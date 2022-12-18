package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class UserSpec extends BaseSpec {
  "User" when {
    "encoder" should {
      "encode user to json" in {
        writeToString(user1) should matchJsonResource("json/model/user.json")
      }
    }

    "decoder" should {
      "decode user from json" in {
        jsonResourceAs[User]("json/model/user.json") shouldBe user1
      }
    }
  }
}
