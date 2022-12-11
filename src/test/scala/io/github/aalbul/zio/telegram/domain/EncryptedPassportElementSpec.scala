package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class EncryptedPassportElementSpec extends BaseSpec {
  "EncryptedPassportElement" when {
    "decoder" should {
      "should decode encrypted passport element json" in {
        jsonResourceAs[EncryptedPassportElement](
          "json/model/encrypted-passport-element.json"
        ) shouldBe encryptedPassportElement1
      }
    }
  }
}
