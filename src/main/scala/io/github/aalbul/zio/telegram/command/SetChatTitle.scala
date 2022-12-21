package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.codecs.boolean

object SetChatTitle {

  /** Constructs minimal [[SetChatTitle]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param title
    *   New chat title, 1-128 characters
    * @return
    *   [[SetChatTitle]] builder
    */
  def of(chatId: String, title: String): SetChatTitle = SetChatTitle(chatId = chatId, title = title)
}

/** Use this method to change the title of a chat. Titles can't be changed for private chats. The bot must be an
  * administrator in the chat for this to work and must have the appropriate administrator rights. Returns True on
  * success.
  */
case class SetChatTitle(chatId: String, title: String) extends Command[Boolean] {
  override val name: String = "setChatTitle"

  override def parameters: ApiParameters = MultipartBody.of(
    stringPart("chat_id", chatId),
    stringPart("title", title)
  )
}
