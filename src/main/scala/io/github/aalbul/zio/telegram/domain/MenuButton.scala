package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{named, CodecMakerConfig, JsonCodecMaker}

object MenuButton {
  implicit val inputMediaJsonCodec: JsonValueCodec[MenuButton] = JsonCodecMaker.make(
    CodecMakerConfig
      .withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
      .withDiscriminatorFieldName(Some("type"))
      .withRequireDiscriminatorFirst(false)
  )
}

sealed trait MenuButton

object MenuButtonCommands {

  /** Constructs minimal [[MenuButtonCommands]]
    * @return
    *   [[MenuButtonCommands]] builder
    */
  def of(): MenuButton = MenuButtonCommands()
}

/** Represents a menu button, which opens the bot's list of commands.
  */
@named("commands")
case class MenuButtonCommands() extends MenuButton

object MenuButtonWebApp {

  /** Constructs minimal [[MenuButtonWebApp]]
    * @param text
    *   Text on the button
    * @param webApp
    *   Description of the Web App that will be launched when the user presses the button. The Web App will be able to
    *   send an arbitrary message on behalf of the user using the method
    *   [[https://core.telegram.org/bots/api#answerwebappquery answerWebAppQuery]].
    * @return
    *   [[MenuButtonWebApp]] builder
    */
  def of(text: String, webApp: WebAppInfo): MenuButton = MenuButtonWebApp(text = text, webApp = webApp)
}

/** Represents a menu button, which launches a [[https://core.telegram.org/bots/webapps Web App]].
  */
@named("web_app")
case class MenuButtonWebApp(text: String, webApp: WebAppInfo) extends MenuButton

object MenuButtonDefault {

  /** Constructs minimal [[MenuButtonDefault]]
    * @return
    *   [[MenuButtonDefault]] builder
    */
  def of(): MenuButton = MenuButtonDefault()
}

/** Describes that no specific value for the menu button was set.
  */
@named("default")
case class MenuButtonDefault() extends MenuButton
