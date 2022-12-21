package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.SetChatDescription.SetChatDescriptionPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object SetChatDescription {
  object SetChatDescriptionPayload {
    implicit val setChatDescriptionPayloadJsonCodec: JsonValueCodec[SetChatDescriptionPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class SetChatDescriptionPayload(chatId: String, description: Option[String])

  /** Constructs minimal [[SetChatDescription]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @return
    *   [[SetChatDescription]] builder
    */
  def of(chatId: String): SetChatDescription = SetChatDescription(
    SetChatDescriptionPayload(
      chatId = chatId,
      description = None
    )
  )
}

/** Use this method to change the description of a group, a supergroup or a channel. The bot must be an administrator in
  * the chat for this to work and must have the appropriate administrator rights. Returns True on success.
  */
case class SetChatDescription(payload: SetChatDescriptionPayload) extends Command[Boolean] {
  override val name: String = "setChatDescription"

  override def parameters: ApiParameters = JsonBody(payload)

  /** New chat description, 0-255 characters
    */
  def withDescription(description: String): SetChatDescription = copy(payload.copy(description = Some(description)))
}
