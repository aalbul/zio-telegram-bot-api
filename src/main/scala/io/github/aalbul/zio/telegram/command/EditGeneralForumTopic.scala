package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.EditGeneralForumTopic.EditGeneralForumTopicPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object EditGeneralForumTopic {
  object EditGeneralForumTopicPayload {
    implicit val editGeneralForumTopicPayloadJsonCodec: JsonValueCodec[EditGeneralForumTopicPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class EditGeneralForumTopicPayload(chatId: String, name: String)

  /** Constructs minimal [[EditGeneralForumTopic]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @param name
    *   New topic name, 1-128 characters
    * @return
    *   [[EditGeneralForumTopic]] builder
    */
  def of(chatId: String, name: String): EditGeneralForumTopic = EditGeneralForumTopic(
    EditGeneralForumTopicPayload(chatId = chatId, name = name)
  )
}

/** Use this method to edit the name of the 'General' topic in a forum supergroup chat. The bot must be an administrator
  * in the chat for this to work and must have can_manage_topics administrator rights. Returns True on success.
  */
case class EditGeneralForumTopic(payload: EditGeneralForumTopicPayload) extends Command[Boolean] {
  override val name: String = "editGeneralForumTopic"
  override def parameters: ApiParameters = JsonBody(payload)
}
