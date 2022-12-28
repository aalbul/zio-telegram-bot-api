package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.domain.Sticker

object GetForumTopicIconStickers {

  /** Constructs minimal [[GetForumTopicIconStickers]] command
    * @return
    *   [[GetForumTopicIconStickers]] builder
    */
  def of: GetForumTopicIconStickers = new GetForumTopicIconStickers
}

/** Use this method to get custom emoji stickers, which can be used as a forum topic icon by any user. Requires no
  * parameters. Returns an Array of [[https://core.telegram.org/bots/api#sticker Sticker]] objects.
  */
class GetForumTopicIconStickers extends Command[Seq[Sticker]] {
  override val name: String = "getForumTopicIconStickers"
  override def parameters: ApiParameters = NoParameters
}
