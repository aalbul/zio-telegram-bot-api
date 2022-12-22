package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.EditForumTopic.EditForumTopicPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object EditForumTopic {
  object EditForumTopicPayload {
    implicit val editForumTopicPayloadJsonCodec: JsonValueCodec[EditForumTopicPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class EditForumTopicPayload(chatId: String, messageThreadId: Long, name: String, iconCustomEmojiId: String)

  /** Constructs minimal [[EditForumTopic]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @param messageThreadId
    *   Unique identifier for the target message thread of the forum topic
    * @param name
    *   New topic name, 1-128 characters
    * @param iconCustomEmojiId
    *   New unique identifier of the custom emoji shown as the topic icon. Use
    *   [[https://core.telegram.org/bots/api#getforumtopiciconstickers getForumTopicIconStickers]] to get all allowed
    *   custom emoji identifiers.
    * @return
    *   [[EditForumTopic]] builder
    */
  def of(chatId: String, messageThreadId: Long, name: String, iconCustomEmojiId: String): EditForumTopic =
    EditForumTopic(
      EditForumTopicPayload(
        chatId = chatId,
        messageThreadId = messageThreadId,
        name = name,
        iconCustomEmojiId = iconCustomEmojiId
      )
    )
}

/** Use this method to edit name and icon of a topic in a forum supergroup chat. The bot must be an administrator in the
  * chat for this to work and must have can_manage_topics administrator rights, unless it is the creator of the topic.
  * Returns True on success.
  */
case class EditForumTopic(payload: EditForumTopicPayload) extends Command[Boolean] {
  override val name: String = "editForumTopic"
  override def parameters: ApiParameters = JsonBody(payload)
}
