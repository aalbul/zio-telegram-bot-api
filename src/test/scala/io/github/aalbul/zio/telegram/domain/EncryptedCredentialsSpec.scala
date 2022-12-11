package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class EncryptedCredentialsSpec extends BaseSpec {
  "EncryptedCredentials" when {
    "decoder" should {
      "should decode encrypted credentials json" in {
        jsonResourceAs[EncryptedCredentials]("json/model/encrypted-credentials.json") shouldBe encryptedCredentials1
      }
    }
  }
}
