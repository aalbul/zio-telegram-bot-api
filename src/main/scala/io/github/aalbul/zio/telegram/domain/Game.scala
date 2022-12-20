package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object Game {
  implicit val gameJsonCodec: JsonValueCodec[Game] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[Game]]
    * @param title
    *   Title of the game
    * @param description
    *   Description of the game
    * @param photo
    *   Photo that will be displayed in the game message in chats.
    * @return
    *   [[Game]] builder
    */
  def of(title: String, description: String, photo: Seq[PhotoSize]): Game = Game(
    title = title,
    description = description,
    photo = photo,
    text = None,
    textEntities = None,
    animation = None
  )
}

/** This object represents a game. Use BotFather to create and edit games, their short names will act as unique
  * identifiers.
  */
case class Game(
  title: String,
  description: String,
  photo: Seq[PhotoSize],
  text: Option[String],
  textEntities: Option[Seq[MessageEntity]],
  animation: Option[Animation]
) {

  /** Brief description of the game or high scores included in the game message. Can be automatically edited to include
    * current high scores for the game when the bot calls
    * [[https://core.telegram.org/bots/api#setgamescore setGameScore]], or manually edited using
    * [[https://core.telegram.org/bots/api#editmessagetext editMessageText]]. 0-4096 characters.
    */
  def withText(text: String): Game = copy(text = Some(text))

  /** Special entities that appear in text, such as usernames, URLs, bot commands, etc.
    */
  def withTextEntities(textEntities: Seq[MessageEntity]): Game = copy(textEntities = Some(textEntities))

  /** Animation that will be displayed in the game message in chats. Upload via [[https://t.me/botfather BotFather]]
    */
  def withAnimation(animation: Animation): Game = copy(animation = Some(animation))
}
