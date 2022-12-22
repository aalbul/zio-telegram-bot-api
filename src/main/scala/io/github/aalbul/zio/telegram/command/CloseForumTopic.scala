package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.CloseForumTopic.CloseForumTopicPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object CloseForumTopic {
  object CloseForumTopicPayload {
    implicit val closeForumTopicPayloadJsonCodec: JsonValueCodec[CloseForumTopicPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class CloseForumTopicPayload(chatId: String, messageThreadId: Long)

  /** Constructs minimal [[CloseForumTopic]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @param messageThreadId
    *   Unique identifier for the target message thread of the forum topic
    * @return
    *   [[CloseForumTopic]] builder
    */
  def of(chatId: String, messageThreadId: Long): CloseForumTopic = CloseForumTopic(
    CloseForumTopicPayload(chatId = chatId, messageThreadId = messageThreadId)
  )
}

/** Use this method to close an open topic in a forum supergroup chat. The bot must be an administrator in the chat for
  * this to work and must have the can_manage_topics administrator rights, unless it is the creator of the topic.
  * Returns True on success.
  */
case class CloseForumTopic(payload: CloseForumTopicPayload) extends Command[Boolean] {
  override val name: String = "closeForumTopic"
  override def parameters: ApiParameters = JsonBody(payload)
}
