package com.github.aalbul.zio.telegram.test

import com.github.aalbul.zio.telegram.domain.{ForceReply, Markup, MessageEntity, MessageEntityTypes, User}

trait Builders {
  lazy val user1: User = User(
    id = 1,
    isBot = false,
    firstName = "John",
    lastName = Some("Wane"),
    username = Some("jwane"),
    languageCode = Some("en"),
    canJoinGroups = Some(true),
    canReadAllGroupMessages = Some(true),
    supportsInlineQueries = Some(false)
  )

  lazy val messageEntity1: MessageEntity = MessageEntity(
    `type` = MessageEntityTypes.Pre,
    offset = 10,
    length = 500,
    url = Some("https://someurl.com"),
    user = Some(user1),
    language = Some("js")
  )

  lazy val forceReplyMarkup1: Markup = ForceReply(
    forceReply = true, inputFieldPlaceholder = Some("some text"), selective = Some(true)
  )
}
