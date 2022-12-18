package io.github.aalbul.zio.telegram.projection

import io.github.aalbul.zio.telegram.domain.{InlineQuery, Update, UpdateType}

object NewInlineQuery {
  implicit val newInlineQueryProjector: UpdateProjector[NewInlineQuery] = new UpdateProjector[NewInlineQuery] {
    override def project(update: Update): Option[NewInlineQuery] = update.inlineQuery.map(NewInlineQuery(_))
    override val updateTypes: Set[UpdateType] = Set(UpdateType.InlineQuery)
  }
}

case class NewInlineQuery(query: InlineQuery) extends UpdateProjection
