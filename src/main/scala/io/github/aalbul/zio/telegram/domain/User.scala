package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object User {

  /** Constructs minimal [[User]]
    * @param id
    *   Unique identifier for this user or bot. This number may have more than 32 significant bits and some programming
    *   languages may have difficulty/silent defects in interpreting it. But it has at most 52 significant bits, so a
    *   64-bit integer or double-precision float type are safe for storing this identifier.
    * @param isBot
    *   True, if this user is a bot
    * @param firstName
    *   User's or bot's first name
    * @return
    *   [[User]] builder
    */
  def of(id: Long, isBot: Boolean, firstName: String): User = User(
    id = id,
    isBot = isBot,
    firstName = firstName,
    lastName = None,
    username = None,
    languageCode = None,
    isPremium = None,
    addedToAttachmentMenu = None,
    canJoinGroups = None,
    canReadAllGroupMessages = None,
    supportsInlineQueries = None
  )

  implicit val userJsonCodec: JsonValueCodec[User] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
}

case class User(
  id: Long,
  isBot: Boolean,
  firstName: String,
  lastName: Option[String],
  username: Option[String],
  languageCode: Option[String],
  isPremium: Option[Boolean],
  addedToAttachmentMenu: Option[Boolean],
  canJoinGroups: Option[Boolean],
  canReadAllGroupMessages: Option[Boolean],
  supportsInlineQueries: Option[Boolean]
) {

  /** User's or bot's last name
    */
  def withLastName(lastName: String): User = copy(lastName = Some(lastName))

  /** User's or bot's username
    */
  def withUsername(username: String): User = copy(username = Some(username))

  /** [[https://en.wikipedia.org/wiki/IETF_language_tag IETF language tag]] of the user's language
    */
  def withLanguageCode(languageCode: String): User = copy(languageCode = Some(languageCode))

  /** True, if this user is a Telegram Premium user
    */
  def withIsPremium(isPremium: Boolean): User = copy(isPremium = Some(isPremium))

  /** True, if this user added the bot to the attachment menu
    */
  def withAddedToAttachmentMenu(addedToAttachmentMenu: Boolean): User =
    copy(addedToAttachmentMenu = Some(addedToAttachmentMenu))

  /** True, if the bot can be invited to groups. Returned only in [[https://core.telegram.org/bots/api#getme getMe]].
    */
  def withCanJoinGroups(canJoinGroups: Boolean): User = copy(canJoinGroups = Some(canJoinGroups))

  /** True, if [[https://core.telegram.org/bots/features#privacy-mode privacy mode]] is disabled for the bot. Returned
    * only in [[https://core.telegram.org/bots/api#getme getMe]].
    */
  def withCanReadAllGroupMessages(canReadAllGroupMessages: Boolean): User =
    copy(canReadAllGroupMessages = Some(canReadAllGroupMessages))

  /** True, if the bot supports inline queries. Returned only in [[https://core.telegram.org/bots/api#getme getMe]].
    */
  def withSupportsInlineQueries(supportsInlineQueries: Boolean): User =
    copy(supportsInlineQueries = Some(supportsInlineQueries))
}
