package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class ContactSpec extends BaseSpec {
  "Contact" when {
    "encode" should {
      "encode contact to json" in {
        writeToString(contact1) should matchJsonResource("json/model/contact.json")
      }
    }

    "decoder" should {
      "decode contact from json" in {
        jsonResourceAs[Contact]("json/model/contact.json") shouldBe contact1
      }
    }
  }
}
