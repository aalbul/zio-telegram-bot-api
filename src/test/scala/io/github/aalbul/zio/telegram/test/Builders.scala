package io.github.aalbul.zio.telegram.test

import io.github.aalbul.zio.telegram.command.FileDescriptor.{pathDescriptor, urlDescriptor}
import io.github.aalbul.zio.telegram.domain.*

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

  lazy val callbackGame1: CallbackGame = CallbackGame()

  lazy val webAppInfo1: WebAppInfo = WebAppInfo(url = "https://webapp.org")

  lazy val forceReplyMarkup1: Markup = ForceReply(
    forceReply = true,
    inputFieldPlaceholder = Some("some text"),
    selective = Some(true)
  )

  lazy val inlineKeyboardButton1: InlineKeyboardButton = InlineKeyboardButton(
    text = "some button 1",
    url = Some("https://google.com/url1"),
    callbackData = Some("some data"),
    webApp = Some(webAppInfo1),
    switchInlineQuery = Some("inline query 1"),
    switchInlineQueryCurrentChat = Some("inline query current chat 1"),
    callbackGame = Some(callbackGame1),
    pay = Some(true)
  )

  lazy val inlineKeyboardButton2: InlineKeyboardButton = InlineKeyboardButton(
    text = "some button 2",
    url = Some("https://google.com/url2"),
    callbackData = Some("another data"),
    webApp = None,
    switchInlineQuery = Some("inline query 2"),
    switchInlineQueryCurrentChat = Some("inline query current chat 2"),
    callbackGame = None,
    pay = Some(false)
  )

  lazy val inlineKeyboardMarkup1: InlineKeyboardMarkup = InlineKeyboardMarkup(
    Seq(Seq(inlineKeyboardButton1), Seq(inlineKeyboardButton2))
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

  lazy val inputMediaAnimation: InputMedia = InputMediaAnimation
    .of(pathDescriptor("/tmp/one.gif"))
    .withThumb(urlDescriptor("https://google.com/animation_thumb.jpg"))
    .withCaption("my gif")
    .withParseMode(ParseModes.MarkdownV2)
    .withCaptionEntities(Seq(messageEntity1))
    .withWidth(1024)
    .withHeight(769)
    .withDuration(346)

  lazy val inputMediaAudio: InputMedia = InputMediaAudio
    .of(pathDescriptor("/tmp/one.mp3"))
    .withThumb(pathDescriptor("/tmp/song_thumb.jpg"))
    .withCaption("my song")
    .withParseMode(ParseModes.HTML)
    .withCaptionEntities(Seq(messageEntity1))
    .withDuration(346)
    .withPerformer("Me")
    .withTitle("Best song ever")

  lazy val inputMediaDocument: InputMedia = InputMediaDocument
    .of(pathDescriptor("/tmp/one.pdf"))
    .withThumb(pathDescriptor("/tmp/document_thumb.jpg"))
    .withCaption("my document")
    .withParseMode(ParseModes.Markdown)
    .withCaptionEntities(Seq(messageEntity1))
    .withDisableContentTypeDetection(true)

  lazy val inputMediaPhoto: InputMedia = InputMediaPhoto
    .of(pathDescriptor("/tmp/one.jpg"))
    .withThumb(pathDescriptor("/tmp/photo_thumb.jpg"))
    .withCaption("my photo")
    .withParseMode(ParseModes.Markdown)
    .withCaptionEntities(Seq(messageEntity1))

  lazy val inputMediaVideo: InputMedia = InputMediaVideo
    .of(pathDescriptor("/tmp/one.mp4"))
    .withThumb(pathDescriptor("/tmp/video_thumb.jpg"))
    .withCaption("my video")
    .withParseMode(ParseModes.HTML)
    .withCaptionEntities(Seq(messageEntity1))
    .withWidth(1920)
    .withHeight(1080)
    .withDuration(980)
    .withSupportsStreaming(true)

  lazy val file1: File =
    File(fileId = "file-1", fileUniqueId = "unique-file-1", fileSize = Some(20), filePath = Some("file-path"))

  lazy val file2: File = File(fileId = "file-2", fileUniqueId = "unique-file-2", fileSize = None, filePath = None)
}
