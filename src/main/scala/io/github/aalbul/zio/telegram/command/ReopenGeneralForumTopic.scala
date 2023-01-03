package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.ReopenGeneralForumTopic.ReopenGeneralForumTopicPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object ReopenGeneralForumTopic {
  object ReopenGeneralForumTopicPayload {
    implicit val reopenGeneralForumTopicPayloadJsonCodec: JsonValueCodec[ReopenGeneralForumTopicPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class ReopenGeneralForumTopicPayload(chatId: String)

  /** Constructs minimal [[ReopenGeneralForumTopic]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @return
    *   [[ReopenGeneralForumTopic]] builder
    */
  def of(chatId: String): ReopenGeneralForumTopic = ReopenGeneralForumTopic(
    ReopenGeneralForumTopicPayload(chatId = chatId)
  )
}

/** Use this method to reopen a closed 'General' topic in a forum supergroup chat. The bot must be an administrator in
  * the chat for this to work and must have the can_manage_topics administrator rights. The topic will be automatically
  * unhidden if it was hidden. Returns True on success.
  */
case class ReopenGeneralForumTopic(payload: ReopenGeneralForumTopicPayload) extends Command[Boolean] {
  override val name: String = "reopenGeneralForumTopic"
  override def parameters: ApiParameters = JsonBody(payload)
}
