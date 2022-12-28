package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.DeleteChatPhoto.DeleteChatPhotoPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object DeleteChatPhoto {

  object DeleteChatPhotoPayload {
    implicit val deleteChatPhotoPayloadJsonCodec: JsonValueCodec[DeleteChatPhotoPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class DeleteChatPhotoPayload(chatId: String)

  /** Constructs minimal [[DeleteChatPhoto]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @return
    *   [[DeleteChatPhoto]] builder
    */
  def of(chatId: String): DeleteChatPhoto = DeleteChatPhoto(DeleteChatPhotoPayload(chatId = chatId))
}

/** Use this method to delete a chat photo. Photos can't be changed for private chats. The bot must be an administrator
  * in the chat for this to work and must have the appropriate administrator rights. Returns True on success.
  */
case class DeleteChatPhoto(payload: DeleteChatPhotoPayload) extends Command[Boolean] {
  override val name: String = "deleteChatPhoto"
  override def parameters: ApiParameters = JsonBody(payload)
}
