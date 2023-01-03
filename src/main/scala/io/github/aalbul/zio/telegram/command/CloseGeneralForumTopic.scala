package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.CloseGeneralForumTopic.CloseGeneralForumTopicPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object CloseGeneralForumTopic {
  object CloseGeneralForumTopicPayload {
    implicit val closeGeneralForumTopicPayloadJsonCodec: JsonValueCodec[CloseGeneralForumTopicPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class CloseGeneralForumTopicPayload(chatId: String)

  /** Constructs minimal [[CloseGeneralForumTopic]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @return
    *   [[CloseGeneralForumTopic]] builder
    */
  def of(chatId: String): CloseGeneralForumTopic = CloseGeneralForumTopic(
    CloseGeneralForumTopicPayload(chatId = chatId)
  )
}

/** Use this method to close an open 'General' topic in a forum supergroup chat. The bot must be an administrator in the
  * chat for this to work and must have the can_manage_topics administrator rights. Returns True on success.
  */
case class CloseGeneralForumTopic(payload: CloseGeneralForumTopicPayload) extends Command[Boolean] {
  override val name: String = "closeGeneralForumTopic"
  override def parameters: ApiParameters = JsonBody(payload)
}
