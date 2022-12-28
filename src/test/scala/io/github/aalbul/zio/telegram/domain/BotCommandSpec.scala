package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class BotCommandSpec extends BaseSpec {
  "BotCommand" when {
    "encoder" should {
      "encode bot command to json" in {
        writeToString(botCommand1) should matchJsonResource("json/model/bot-command.json")
      }
    }

    "decoder" should {
      "decode bot command from json" in {
        jsonResourceAs[BotCommand]("json/model/bot-command.json") shouldBe botCommand1
      }
    }
  }
}
