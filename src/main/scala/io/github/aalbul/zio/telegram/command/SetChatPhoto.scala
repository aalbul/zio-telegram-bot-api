package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object SetChatPhoto {

  /** Constructs minimal [[SetChatPhoto]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param photo
    *   New chat photo, uploaded using multipart/form-data
    * @return
    *   [[SetChatPhoto]] builder
    */
  def of(chatId: String, photo: FileDescriptor): SetChatPhoto = SetChatPhoto(chatId = chatId, photo = photo)
}

/** Use this method to set a new profile photo for the chat. Photos can't be changed for private chats. The bot must be
  * an administrator in the chat for this to work and must have the appropriate administrator rights. Returns True on
  * success.
  */
case class SetChatPhoto(chatId: String, photo: FileDescriptor) extends Command[Boolean] {
  override val name: String = "setChatPhoto"

  override def parameters: ApiParameters = MultipartBody.of(
    stringPart("chat_id", chatId),
    photo.asMultipart("photo")
  )
}
