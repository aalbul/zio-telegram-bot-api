package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.DeleteForumTopic.DeleteForumTopicPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object DeleteForumTopic {
  object DeleteForumTopicPayload {
    implicit val deleteForumTopicPayloadJsonCodec: JsonValueCodec[DeleteForumTopicPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class DeleteForumTopicPayload(chatId: String, messageThreadId: Long)

  /** Constructs minimal [[DeleteForumTopic]] command
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @param messageThreadId
    *   Unique identifier for the target message thread of the forum topic
    * @return
    *   [[DeleteForumTopic]] builder
    */
  def of(chatId: String, messageThreadId: Long): DeleteForumTopic = DeleteForumTopic(
    DeleteForumTopicPayload(chatId = chatId, messageThreadId = messageThreadId)
  )
}

/** Use this method to delete a forum topic along with all its messages in a forum supergroup chat. The bot must be an
  * administrator in the chat for this to work and must have the can_delete_messages administrator rights. Returns True
  * on success.
  */
case class DeleteForumTopic(payload: DeleteForumTopicPayload) extends Command[Boolean] {
  override val name: String = "deleteForumTopic"
  override def parameters: ApiParameters = JsonBody(payload)
}
