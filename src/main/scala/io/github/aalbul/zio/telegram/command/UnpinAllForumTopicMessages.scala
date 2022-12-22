package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.UnpinAllForumTopicMessages.UnpinAllForumTopicMessagesPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object UnpinAllForumTopicMessages {
  object UnpinAllForumTopicMessagesPayload {
    implicit val unpinAllForumTopicMessagesPayloadJsonCodec: JsonValueCodec[UnpinAllForumTopicMessagesPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class UnpinAllForumTopicMessagesPayload(chatId: String, messageThreadId: Long)

  /** Constructs minimal [[UnpinAllForumTopicMessages]] command
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @param messageThreadId
    *   Unique identifier for the target message thread of the forum topic
    * @return
    *   [[UnpinAllForumTopicMessages]] builder
    */
  def of(chatId: String, messageThreadId: Long): UnpinAllForumTopicMessages = UnpinAllForumTopicMessages(
    UnpinAllForumTopicMessagesPayload(chatId = chatId, messageThreadId = messageThreadId)
  )
}

/** Use this method to clear the list of pinned messages in a forum topic. The bot must be an administrator in the chat
  * for this to work and must have the can_pin_messages administrator right in the supergroup. Returns True on success.
  */
case class UnpinAllForumTopicMessages(payload: UnpinAllForumTopicMessagesPayload) extends Command[Boolean] {
  override val name: String = "unpinAllForumTopicMessages"
  override def parameters: ApiParameters = JsonBody(payload)
}
