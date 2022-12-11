package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class ContactSpec extends BaseSpec {
  "Contact" when {
    "decoder" should {
      "should decode contact json" in {
        jsonResourceAs[Contact]("json/model/contact.json") shouldBe contact1
      }
    }
  }
}
