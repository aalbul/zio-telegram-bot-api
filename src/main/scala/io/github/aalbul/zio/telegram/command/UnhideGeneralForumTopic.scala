package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.UnhideGeneralForumTopic.UnhideGeneralForumTopicPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object UnhideGeneralForumTopic {
  object UnhideGeneralForumTopicPayload {
    implicit val unhideGeneralForumTopicPayloadJsonCodec: JsonValueCodec[UnhideGeneralForumTopicPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class UnhideGeneralForumTopicPayload(chatId: String)

  /** Constructs minimal [[UnhideGeneralForumTopic]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @return
    *   [[UnhideGeneralForumTopic]] builder
    */
  def of(chatId: String): UnhideGeneralForumTopic = UnhideGeneralForumTopic(
    UnhideGeneralForumTopicPayload(chatId = chatId)
  )
}

/** Use this method to unhide the 'General' topic in a forum supergroup chat. The bot must be an administrator in the
  * chat for this to work and must have the can_manage_topics administrator rights. Returns True on success.
  */
case class UnhideGeneralForumTopic(payload: UnhideGeneralForumTopicPayload) extends Command[Boolean] {
  override val name: String = "unhideGeneralForumTopic"
  override def parameters: ApiParameters = JsonBody(payload)
}
