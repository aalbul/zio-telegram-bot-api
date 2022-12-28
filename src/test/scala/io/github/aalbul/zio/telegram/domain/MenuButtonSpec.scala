package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class MenuButtonSpec extends BaseSpec {
  "MenuButton" when {
    "encoder" should {
      "should encode MenuButtonCommands to json" in {
        writeToString(menuButton1) should matchJsonResource("json/model/menu-button-commands.json")
      }

      "should encode MenuButtonWebApp to json" in {
        writeToString(menuButton2) should matchJsonResource("json/model/menu-button-web-app.json")
      }

      "should encode MenuButtonDefault to json" in {
        writeToString(menuButton3) should matchJsonResource("json/model/menu-button-default.json")
      }
    }

    "decoder" should {
      "should decode MenuButtonCommands from json" in {
        jsonResourceAs[MenuButton]("json/model/menu-button-commands.json") shouldBe menuButton1
      }

      "should decode MenuButtonWebApp from json" in {
        jsonResourceAs[MenuButton]("json/model/menu-button-web-app.json") shouldBe menuButton2
      }

      "should decode MenuButtonDefault from json" in {
        jsonResourceAs[MenuButton]("json/model/menu-button-default.json") shouldBe menuButton3
      }
    }
  }
}
