package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.SendChatAction.SendChatActionPayload
import io.github.aalbul.zio.telegram.domain.ChatAction
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object SendChatAction {
  object SendChatActionPayload {
    implicit val sendChatActionPayloadJsonCodec: JsonValueCodec[SendChatActionPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class SendChatActionPayload(chatId: String, messageThreadId: Option[Long], action: ChatAction)

  /** Constructs minimal [[SendChatAction]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param action
    *   Type of action to broadcast. Choose one, depending on what the user is about to receive: typing for
    *   [[https://core.telegram.org/bots/api#sendmessage text messages]], upload_photo for
    *   [[https://core.telegram.org/bots/api#sendphoto photos]], record_video or upload_video for
    *   [[https://core.telegram.org/bots/api#sendvideo videos]], record_voice or upload_voice for
    *   [[https://core.telegram.org/bots/api#sendvoice voice notes]], upload_document for
    *   [[https://core.telegram.org/bots/api#senddocument general files]], choose_sticker for
    *   [[https://core.telegram.org/bots/api#sendsticker stickers]], find_location for
    *   [[https://core.telegram.org/bots/api#sendlocation location data]], record_video_note or upload_video_note for
    *   [[https://core.telegram.org/bots/api#sendvideonote video notes]].
    *
    * @return
    *   [[SendChatAction]] builder
    */
  def of(chatId: String, action: ChatAction): SendChatAction = SendChatAction(
    SendChatActionPayload(chatId = chatId, messageThreadId = None, action = action)
  )
}

/** Use this method when you need to tell the user that something is happening on the bot's side. The status is set for
  * 5 seconds or less (when a message arrives from your bot, Telegram clients clear its typing status). Returns ''True''
  * on success.
  *
  * Example: The [[https://t.me/imagebot ImageBot]] needs some time to process a request and upload the image. Instead
  * of sending a text message along the lines of “Retrieving image, please wait…”, the bot may use [[SendChatAction]]
  * with ''action = upload_photo''. The user will see a “sending photo” status for the bot.
  *
  * We only recommend using this method when a response from the bot will take a noticeable amount of time to arrive.
  */
case class SendChatAction(payload: SendChatActionPayload) extends Command[Boolean] {
  override val name: String = "sendChatAction"

  override def parameters: ApiParameters = JsonBody(payload)

  /** Unique identifier for the target message thread; supergroups only
    */
  def withMessageThreadId(messageThreadId: Long): SendChatAction = copy(
    payload.copy(messageThreadId = Some(messageThreadId))
  )
}
