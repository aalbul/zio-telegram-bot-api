package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.GetCustomEmojiStickers.GetCustomEmojiStickersPayload
import io.github.aalbul.zio.telegram.domain.Sticker

object GetCustomEmojiStickers {
  object GetCustomEmojiStickersPayload {
    implicit val getCustomEmojiStickersPayloadJsonCodec: JsonValueCodec[GetCustomEmojiStickersPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class GetCustomEmojiStickersPayload(customEmojiIds: Seq[String])

  /** Constructs minimal [[GetCustomEmojiStickers]] command
    * @param customEmojiIds
    *   List of custom emoji identifiers. At most 200 custom emoji identifiers can be specified.
    * @return
    *   [[GetCustomEmojiStickers]] builder
    */
  def of(customEmojiIds: Seq[String]): GetCustomEmojiStickers = GetCustomEmojiStickers(
    GetCustomEmojiStickersPayload(customEmojiIds = customEmojiIds)
  )
}

/** Use this method to get information about custom emoji stickers by their identifiers. Returns an Array of
  * [[https://core.telegram.org/bots/api#sticker Sticker]] objects.
  */
case class GetCustomEmojiStickers(payload: GetCustomEmojiStickersPayload) extends Command[Seq[Sticker]] {
  override val name: String = "getCustomEmojiStickers"
  override def parameters: ApiParameters = JsonBody(payload)
}
