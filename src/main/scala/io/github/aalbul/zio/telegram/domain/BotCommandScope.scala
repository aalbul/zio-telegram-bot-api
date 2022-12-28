package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{named, CodecMakerConfig, JsonCodecMaker}

object BotCommandScope {
  implicit val botCommandScopeJsonCodec: JsonValueCodec[BotCommandScope] = JsonCodecMaker.make(
    CodecMakerConfig
      .withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
      .withDiscriminatorFieldName(Some("type"))
      .withRequireDiscriminatorFirst(false)
  )
}

sealed trait BotCommandScope

object BotCommandScopeDefault {

  /** Constructs minimal [[BotCommandScopeDefault]] object
    *
    * @return
    *   [[BotCommandScopeDefault]] builder
    */
  def of: BotCommandScope = BotCommandScopeDefault()
}

/** Represents the default [[https://core.telegram.org/bots/api#botcommandscope scope]] of bot commands. Default
  * commands are used if no commands with a
  * [[https://core.telegram.org/bots/api#determining-list-of-commands narrower scope]] are specified for the user.
  */
@named("default")
case class BotCommandScopeDefault() extends BotCommandScope

object BotCommandScopeAllPrivateChats {

  /** Constructs minimal [[BotCommandScopeAllPrivateChats]] object
    *
    * @return
    *   [[BotCommandScopeAllPrivateChats]] builder
    */
  def of: BotCommandScope = BotCommandScopeAllPrivateChats()
}

/** Represents the [[https://core.telegram.org/bots/api#botcommandscope scope]] of bot commands, covering all private
  * chats.
  */
@named("all_private_chats")
case class BotCommandScopeAllPrivateChats() extends BotCommandScope

object BotCommandScopeAllGroupChats {

  /** Constructs minimal [[BotCommandScopeAllGroupChats]] object
    *
    * @return
    *   [[BotCommandScopeAllGroupChats]] builder
    */
  def of: BotCommandScope = BotCommandScopeAllGroupChats()
}

/** Represents the [[https://core.telegram.org/bots/api#botcommandscope scope]] of bot commands, covering all group and
  * supergroup chats.
  */
@named("all_group_chats")
case class BotCommandScopeAllGroupChats() extends BotCommandScope

object BotCommandScopeAllChatAdministrators {

  /** Constructs minimal [[BotCommandScopeAllChatAdministrators]] object
    *
    * @return
    *   [[BotCommandScopeAllChatAdministrators]] builder
    */
  def of: BotCommandScope = BotCommandScopeAllChatAdministrators()
}

/** Represents the [[https://core.telegram.org/bots/api#botcommandscope scope]] of bot commands, covering all group and
  * supergroup chat administrators.
  */
@named("all_chat_administrators")
case class BotCommandScopeAllChatAdministrators() extends BotCommandScope

object BotCommandScopeChat {

  /** Constructs minimal [[BotCommandScopeChat]] object
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @return
    *   [[BotCommandScopeChat]] builder
    */
  def of(chatId: String): BotCommandScope = BotCommandScopeChat(chatId)
}

/** Represents the [[https://core.telegram.org/bots/api#botcommandscope scope]] of bot commands, covering a specific
  * chat.
  */
@named("chat")
case class BotCommandScopeChat(chatId: String) extends BotCommandScope

object BotCommandScopeChatAdministrators {

  /** Constructs minimal [[BotCommandScopeChatAdministrators]] object
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @return
    *   [[BotCommandScopeChatAdministrators]] builder
    */
  def of(chatId: String): BotCommandScope = BotCommandScopeChatAdministrators(chatId)
}

/** Represents the [[https://core.telegram.org/bots/api#botcommandscope scope]] of bot commands, covering all
  * administrators of a specific group or supergroup chat.
  */
@named("chat_administrators")
case class BotCommandScopeChatAdministrators(chatId: String) extends BotCommandScope

object BotCommandScopeChatMember {

  /** Constructs minimal [[BotCommandScopeChatMember]] object
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @param userId
    *   Unique identifier of the target user
    * @return
    *   [[BotCommandScopeChatMember]] builder
    */
  def of(chatId: String, userId: Long): BotCommandScope = BotCommandScopeChatMember(chatId = chatId, userId = userId)
}

/** Represents the [[https://core.telegram.org/bots/api#botcommandscope scope]] of bot commands, covering a specific
  * member of a group or supergroup chat.
  */
@named("chat_member")
case class BotCommandScopeChatMember(chatId: String, userId: Long) extends BotCommandScope
