package io.github.aalbul.zio.telegram.projection

import io.github.aalbul.zio.telegram.domain.UpdateTypes.UpdateType
import io.github.aalbul.zio.telegram.domain.{ChatJoinRequest, Update, UpdateTypes}

object NewChatJoinRequest {
  implicit val newChatJoinRequestProjector: UpdateProjector[NewChatJoinRequest] =
    new UpdateProjector[NewChatJoinRequest] {
      override def project(update: Update): Option[NewChatJoinRequest] =
        update.chatJoinRequest.map(NewChatJoinRequest(_))

      override val updateTypes: Set[UpdateType] = Set(UpdateTypes.ChatJoinRequest)
    }
}

case class NewChatJoinRequest(request: ChatJoinRequest) extends UpdateProjection
