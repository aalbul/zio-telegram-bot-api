package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object MessageEntity {
  implicit val messageEntityJsonCodec: JsonValueCodec[MessageEntity] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  implicit val seqMessageEntityJsonCodec: JsonValueCodec[Seq[MessageEntity]] = JsonCodecMaker.make

  /** Constructs minimal [[MessageEntity]]
    * @param `type`
    *   Type of the entity. Currently, can be “mention” (@username), “hashtag” (#hashtag), “cashtag” ($USD),
    *   “bot_command” (/start@jobs_bot), “url” (https://telegram.org), “email” (do-not-reply@telegram.org),
    *   “phone_number” (+1-212-555-0123), “bold” (bold text), “italic” (italic text), “underline” (underlined text),
    *   “strikethrough” (strikethrough text), “spoiler” (spoiler message), “code” (monowidth string), “pre” (monowidth
    *   block), “text_link” (for clickable text URLs), “text_mention” (for users
    *   [[https://telegram.org/blog/edit#new-mentions without usernames]]), “custom_emoji” (for inline custom emoji
    *   stickers)
    * @param offset
    *   Offset in [[https://core.telegram.org/api/entities#entity-length UTF-16 code units]] to the start of the entity
    * @param length
    *   Length of the entity in [[https://core.telegram.org/api/entities#entity-length UTF-16 code units]]
    * @return
    *   [[MessageEntity]] builder
    */
  def of(`type`: MessageEntityType, offset: Long, length: Long): MessageEntity = MessageEntity(
    `type` = `type`,
    offset = offset,
    length = length,
    url = None,
    user = None,
    language = None,
    customEmojiId = None
  )
}

/** This object represents one special entity in a text message. For example, hashtags, usernames, URLs, etc.
  */
case class MessageEntity(
  `type`: MessageEntityType,
  offset: Long,
  length: Long,
  url: Option[String],
  user: Option[User],
  language: Option[String],
  customEmojiId: Option[String]
) {

  /** For “text_link” only, URL that will be opened after user taps on the text
    */
  def withUrl(url: String): MessageEntity = copy(url = Some(url))

  /** For “text_mention” only, the mentioned user
    */
  def withUser(user: User): MessageEntity = copy(user = Some(user))

  /** For “pre” only, the programming language of the entity text
    */
  def withLanguage(language: String): MessageEntity = copy(language = Some(language))

  /** For “custom_emoji” only, unique identifier of the custom emoji. Use
    * [[https://core.telegram.org/bots/api#getcustomemojistickers getCustomEmojiStickers]] to get full information about
    * the sticker
    */
  def withCustomEmojiId(customEmojiId: String): MessageEntity = copy(customEmojiId = Some(customEmojiId))
}
