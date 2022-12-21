package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object DeleteChatPhoto {

  /** Constructs minimal [[DeleteChatPhoto]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @return
    *   [[DeleteChatPhoto]] builder
    */
  def of(chatId: String): DeleteChatPhoto = DeleteChatPhoto(chatId)
}

/** Use this method to delete a chat photo. Photos can't be changed for private chats. The bot must be an administrator
  * in the chat for this to work and must have the appropriate administrator rights. Returns True on success.
  */
case class DeleteChatPhoto(chatId: String) extends Command[Boolean] {
  override val name: String = "deleteChatPhoto"

  override def parameters: ApiParameters = MultipartBody.of(
    stringPart("chat_id", chatId)
  )
}
