package io.github.aalbul.zio.telegram.projection

import io.github.aalbul.zio.telegram.domain.UpdateTypes.UpdateType
import io.github.aalbul.zio.telegram.domain.{ShippingQuery, Update, UpdateTypes}

object NewShippingQuery {
  implicit val newShippingQueryProjector: UpdateProjector[NewShippingQuery] = new UpdateProjector[NewShippingQuery] {
    override def project(update: Update): Option[NewShippingQuery] = update.shippingQuery.map(NewShippingQuery(_))
    override val updateTypes: Set[UpdateType] = Set(UpdateTypes.ShippingQuery)
  }
}

case class NewShippingQuery(shippingQuery: ShippingQuery) extends UpdateProjection
