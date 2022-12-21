package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.CreateForumTopic.CreateForumTopicPayload
import io.github.aalbul.zio.telegram.domain.ForumTopic

object CreateForumTopic {
  object CreateForumTopicPayload {
    implicit val createForumTopicPayloadJsonCodec: JsonValueCodec[CreateForumTopicPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class CreateForumTopicPayload(
    chatId: String,
    name: String,
    iconColor: Option[Long],
    iconCustomEmojiId: Option[String]
  )

  /** Constructs minimal [[CreateForumTopic]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @param name
    *   Topic name, 1-128 characters
    * @return
    *   [[CreateForumTopic]] builder
    */
  def of(chatId: String, name: String): CreateForumTopic = CreateForumTopic(
    CreateForumTopicPayload(
      chatId = chatId,
      name = name,
      iconColor = None,
      iconCustomEmojiId = None
    )
  )
}

/** Use this method to create a topic in a forum supergroup chat. The bot must be an administrator in the chat for this
  * to work and must have the can_manage_topics administrator rights. Returns information about the created topic as a
  * [[https://core.telegram.org/bots/api#forumtopic ForumTopic]] object.
  */
case class CreateForumTopic(payload: CreateForumTopicPayload) extends Command[ForumTopic] {
  override val name: String = "createForumTopic"
  override def parameters: ApiParameters = JsonBody(payload)

  /** Color of the topic icon in RGB format. Currently, must be one of 7322096 (0x6FB9F0), 16766590 (0xFFD67E), 13338331
    * (0xCB86DB), 9367192 (0x8EEE98), 16749490 (0xFF93B2), or 16478047 (0xFB6F5F)
    */
  def withIconColor(iconColor: Long): CreateForumTopic = copy(payload.copy(iconColor = Some(iconColor)))

  /** Unique identifier of the custom emoji shown as the topic icon. Use
    * [[https://core.telegram.org/bots/api#getforumtopiciconstickers getForumTopicIconStickers]] to get all allowed
    * custom emoji identifiers.
    */
  def withIconCustomEmojiId(iconCustomEmojiId: String): CreateForumTopic = copy(
    payload.copy(iconCustomEmojiId = Some(iconCustomEmojiId))
  )
}
