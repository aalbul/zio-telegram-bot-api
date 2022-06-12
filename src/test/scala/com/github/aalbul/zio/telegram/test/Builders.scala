package com.github.aalbul.zio.telegram.test

import com.github.aalbul.zio.telegram.domain.{Chat, ChatTypes, ForceReply, Markup, Message, MessageEntity, MessageEntityTypes, Update, User}

import java.time.Instant

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
    forceReply = true,
    inputFieldPlaceholder = Some("some text"),
    selective = Some(true)
  )

  lazy val chat1: Chat = Chat.of(id = 81, `type` = ChatTypes.Supergroup)

  lazy val chat2: Chat = Chat.of(id = 82, `type` = ChatTypes.Private)

  lazy val chat3: Chat = Chat.of(id = 83, `type` = ChatTypes.Supergroup)

  lazy val message1: Message =
    Message.of(messageId = 15, date = Instant.parse("2022-06-12T10:15:30.00Z"), chat = chat1)

  lazy val message2: Message =
    Message.of(messageId = 16, date = Instant.parse("2022-06-12T10:41:32.00Z"), chat = chat2)

  lazy val message3: Message =
    Message.of(messageId = 17, date = Instant.parse("2022-06-13T06:15:11.00Z"), chat = chat3)

  lazy val message4: Message =
    Message.of(messageId = 18, date = Instant.parse("2022-06-13T06:18:10.00Z"), chat = chat3)

  lazy val update1: Update = Update.of(updateId = 66).copy(message = Some(message1))
  lazy val update2: Update = Update.of(updateId = 67).copy(message = Some(message2))
  lazy val update3: Update = Update.of(updateId = 68).copy(message = Some(message3))
  lazy val update4: Update = Update.of(updateId = 69).copy(message = Some(message4))
}
