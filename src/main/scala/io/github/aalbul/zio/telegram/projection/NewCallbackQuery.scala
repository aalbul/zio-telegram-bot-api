package io.github.aalbul.zio.telegram.projection

import io.github.aalbul.zio.telegram.domain.{CallbackQuery, Update, UpdateType}

object NewCallbackQuery {
  implicit val newCallbackQueryProjector: UpdateProjector[NewCallbackQuery] = new UpdateProjector[NewCallbackQuery] {
    override def project(update: Update): Option[NewCallbackQuery] = update.callbackQuery.map(NewCallbackQuery(_))

    override val updateTypes: Set[UpdateType] = Set(UpdateType.CallbackQuery)
  }
}

case class NewCallbackQuery(callbackQuery: CallbackQuery) extends UpdateProjection
