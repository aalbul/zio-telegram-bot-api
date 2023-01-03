package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.HideGeneralForumTopic.HideGeneralForumTopicPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object HideGeneralForumTopic {
  object HideGeneralForumTopicPayload {
    implicit val hideGeneralForumTopicPayloadJsonCodec: JsonValueCodec[HideGeneralForumTopicPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class HideGeneralForumTopicPayload(chatId: String)

  /** Constructs minimal [[HideGeneralForumTopic]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @return
    *   [[HideGeneralForumTopic]] builder
    */
  def of(chatId: String): HideGeneralForumTopic = HideGeneralForumTopic(HideGeneralForumTopicPayload(chatId = chatId))
}

/** Use this method to hide the 'General' topic in a forum supergroup chat. The bot must be an administrator in the chat
  * for this to work and must have the can_manage_topics administrator rights. The topic will be automatically closed if
  * it was open. Returns True on success.
  */
case class HideGeneralForumTopic(payload: HideGeneralForumTopicPayload) extends Command[Boolean] {
  override val name: String = "hideGeneralForumTopic"
  override def parameters: ApiParameters = JsonBody(payload)
}
