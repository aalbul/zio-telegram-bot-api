package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.ReopenForumTopic.ReopenForumTopicPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object ReopenForumTopic {
  object ReopenForumTopicPayload {
    implicit val reopenForumTopicPayloadJsonCodec: JsonValueCodec[ReopenForumTopicPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class ReopenForumTopicPayload(chatId: String, messageThreadId: Long)

  /** Constructs minimal [[ReopenForumTopic]] command
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @param messageThreadId
    *   Unique identifier for the target message thread of the forum topic
    * @return
    *   [[ReopenForumTopic]] builder
    */
  def of(chatId: String, messageThreadId: Long): ReopenForumTopic = ReopenForumTopic(
    ReopenForumTopicPayload(chatId = chatId, messageThreadId = messageThreadId)
  )
}

/** Use this method to reopen a closed topic in a forum supergroup chat. The bot must be an administrator in the chat
  * for this to work and must have the can_manage_topics administrator rights, unless it is the creator of the topic.
  * Returns True on success.
  */
case class ReopenForumTopic(payload: ReopenForumTopicPayload) extends Command[Boolean] {
  override val name: String = "reopenForumTopic"
  override def parameters: ApiParameters = JsonBody(payload)
}
