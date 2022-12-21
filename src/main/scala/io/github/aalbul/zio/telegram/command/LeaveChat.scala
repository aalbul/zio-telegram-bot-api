package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.LeaveChat.LeaveChatPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.codecs.boolean

object LeaveChat {
  object LeaveChatPayload {
    implicit val leaveChatPayloadJsonCodec: JsonValueCodec[LeaveChatPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class LeaveChatPayload(chatId: String)

  /** Constructs minimal [[LeaveChat]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup or channel (in the format
    *   \@channelusername)
    * @return
    *   [[LeaveChat]] builder
    */
  def of(chatId: String): LeaveChat = LeaveChat(LeaveChatPayload(chatId = chatId))
}

/** Use this method for your bot to leave a group, supergroup or channel. Returns True on success.
  */
case class LeaveChat(payload: LeaveChatPayload) extends Command[Boolean] {
  override val name: String = "leaveChat"

  override def parameters: ApiParameters = JsonBody(payload)
}
