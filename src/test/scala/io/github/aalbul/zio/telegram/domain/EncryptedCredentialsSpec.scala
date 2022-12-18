package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class EncryptedCredentialsSpec extends BaseSpec {
  "EncryptedCredentials" when {
    "encode" should {
      "encode encrypted credentials to json" in {
        writeToString(encryptedCredentials1) should matchJsonResource("json/model/encrypted-credentials.json")
      }
    }

    "decoder" should {
      "decode encrypted credentials from json" in {
        jsonResourceAs[EncryptedCredentials](
          "json/model/encrypted-credentials.json"
        ) shouldBe encryptedCredentials1
      }
    }
  }
}
