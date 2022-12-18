package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class KeyboardButtonPollTypeSpec extends BaseSpec {
  "KeyboardButtonPollType" when {
    "encoder" should {
      "encode keyboard button poll type to json" in {
        writeToString(keyboardButtonPollType1) should matchJsonResource("json/model/keyboard-button-poll-type.json")
      }
    }

    "decoder" should {
      "decode keyboard button poll type from json" in {
        jsonResourceAs[KeyboardButtonPollType](
          "json/model/keyboard-button-poll-type.json"
        ) shouldBe keyboardButtonPollType1
      }
    }
  }
}
