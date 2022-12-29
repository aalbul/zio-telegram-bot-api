package io.github.aalbul.zio.telegram.domain

object InlineQueryResultGame {

  /** Constructs minimal [[InlineQueryResultGame]]
    * @param id
    *   Unique identifier for this result, 1-64 bytes
    * @param gameShortName
    *   Short name of the game
    * @return
    *   [[InlineQueryResultGame]] builder
    */
  def of(id: String, gameShortName: String): InlineQueryResultGame = InlineQueryResultGame(
    id = id,
    gameShortName = gameShortName,
    replyMarkup = None
  )
}

/** Represents a [[https://core.telegram.org/bots/api#games Game]].
  *
  * Note: This will only work in Telegram versions released after October 1, 2016. Older clients will not display any
  * inline results if a game result is among them.
  */
case class InlineQueryResultGame(
  id: String,
  gameShortName: String,
  replyMarkup: Option[InlineKeyboardMarkup],
  override val `type`: String = "game"
) extends InlineQueryResult {

  /** [[https://core.telegram.org/bots/features#inline-keyboards Inline keyboard]] attached to the message
    */
  def withReplyMarkup(replyMarkup: InlineKeyboardMarkup): InlineQueryResultGame =
    copy(replyMarkup = Some(replyMarkup))
}
