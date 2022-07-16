package io.github.aalbul.zio.telegram.test

import io.github.aalbul.zio.telegram.command.FileDescriptor.{pathDescriptor, urlDescriptor}
import io.github.aalbul.zio.telegram.domain.*
import io.github.aalbul.zio.telegram.projection.UpdateProjection
import io.github.aalbul.zio.telegram.projection.message.*
import io.github.aalbul.zio.telegram.projection.message.MediaMessageProjection.Media
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

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

  lazy val loginUrl1: LoginUrl = LoginUrl
    .of("https://google.com/login")
    .withBotUsername("bot-1")
    .withForwardText("forward text")
    .withRequestWriteAccess(true)

  lazy val inlineKeyboardButton1: InlineKeyboardButton = InlineKeyboardButton(
    text = "some button 1",
    url = Some("https://google.com/url1"),
    callbackData = Some("some data"),
    webApp = Some(webAppInfo1),
    loginUrl = Some(loginUrl1),
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
    loginUrl = Some(loginUrl1),
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

  lazy val instant1: Instant = Instant.parse("2022-06-12T10:15:30.00Z")

  lazy val instant2: Instant = Instant.parse("2022-06-12T10:41:32.00Z")

  lazy val instant3: Instant = Instant.parse("2022-06-13T06:15:11.00Z")

  private val instant4: Instant = Instant.parse("2022-06-13T06:18:10.00Z")

  lazy val message1: Message = Message.of(messageId = 15, date = instant1, chat = chat1)

  lazy val message2: Message = Message.of(messageId = 16, date = instant2, chat = chat2)

  lazy val message3: Message = Message.of(messageId = 17, date = instant3, chat = chat3)

  lazy val message4: Message = Message.of(messageId = 18, date = instant4, chat = chat3)

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

  lazy val chatPermissions1: ChatPermissions = ChatPermissions(
    canSendMessages = Some(true),
    canSendMediaMessages = Some(false),
    canSendPolls = Some(true),
    canSendOtherMessages = Some(false),
    canAddWebPagePreviews = Some(true),
    canChangeInfo = Some(false),
    canInviteUsers = Some(false),
    canPinMessages = Some(true)
  )

  lazy val photoSize1: PhotoSize = PhotoSize(
    fileId = "photo-size-1",
    fileUniqueId = "unique-photo-size-1",
    width = 300,
    height = 200,
    fileSize = Some(11)
  )

  lazy val photoSize2: PhotoSize = PhotoSize(
    fileId = "photo-size-2",
    fileUniqueId = "unique-photo-size-2",
    width = 1300,
    height = 500,
    fileSize = Some(421)
  )

  lazy val animation1: Animation = Animation(
    fileId = "animation-file-1",
    fileUniqueId = "unique-animation-file-1",
    width = 640,
    height = 480,
    duration = 50,
    thumb = Some(photoSize1),
    fileName = Some("animation-file"),
    mimeType = Some("image/gif"),
    fileSize = Some(450)
  )

  lazy val document1: Document = Document(
    fileId = "document-file-1",
    fileUniqueId = "unique-document-file-1",
    thumb = Some(photoSize2),
    fileName = Some("document-1"),
    mimeType = None,
    fileSize = Some(5000)
  )

  lazy val audio1: Audio = Audio(
    fileId = "audio-file-1",
    fileUniqueId = "unique-audio-file-1",
    duration = 422,
    performer = Some("Amia Venera Landscape"),
    title = Some("A New Aurora"),
    fileName = Some("02 - A New Aurora.flac"),
    mimeType = Some("audio/x-flac,audio/flac"),
    fileSize = Some(300000000),
    thumb = Some(photoSize1)
  )

  lazy val contact1: Contact = Contact(
    phoneNumber = "+31680822212",
    firstName = "John",
    lastName = Some("Wick"),
    userId = Some(1),
    vcard = Some("vcard")
  )

  lazy val dice1: Dice = Dice(
    emoji = "🏀",
    value = 2
  )

  lazy val game1: Game = Game(
    title = "Guess who",
    description =
      "Guess Who? is a two-player board game where players each guess the identity of the other's chosen character",
    photo = Seq(photoSize1, photoSize2),
    text = Some("Some text"),
    textEntities = Some(Seq(messageEntity1)),
    animation = Some(animation1)
  )

  lazy val location1: Location = Location(
    longitude = 52.22,
    latitude = 4.55,
    horizontalAccuracy = Some(1.23),
    livePeriod = Some(50),
    heading = Some(23),
    proximityAlertRadius = Some(51)
  )

  lazy val poll1: Poll = Poll(
    id = "poll-1",
    question = "Favorite fruit",
    options = Seq(
      PollOption(text = "Apple", voterCount = 10),
      PollOption(text = "Peer", voterCount = 3),
      PollOption(text = "Watermelon", voterCount = 8)
    ),
    totalVoterCount = 21,
    isClosed = true,
    isAnonymous = false,
    `type` = PollTypes.Quiz,
    allowsMultipleAnswers = false,
    correctOptionId = Some(0),
    explanation = Some("Which is your favorite fruit of all time?"),
    explanationEntities = Some(Seq(messageEntity1)),
    openPeriod = Some(24),
    closeDate = Some(500)
  )

  lazy val maskPosition1: MaskPosition = MaskPosition(
    point = "point-1",
    xShift = 10.2,
    yShift = 5.4,
    scale = 1.3
  )

  lazy val sticker1: Sticker = Sticker(
    fileId = "sticker-1",
    fileUniqueId = "unique-sticker-1",
    width = 80,
    height = 50,
    isAnimated = true,
    isVideo = false,
    thumb = Some(photoSize1),
    emoji = Some("🎰"),
    setName = Some("set-1"),
    maskPosition = Some(maskPosition1)
  )

  lazy val venue1: Venue = Venue(
    location = location1,
    title = "NEMO Science Museum",
    address = "Oosterdok 2, 1011 VX Amsterdam",
    foursquareId = Some("fsq1"),
    foursquareType = Some("fsq-t-1"),
    googlePlaceId = Some("gpi1"),
    googlePlaceType = Some("gp-t-1")
  )

  lazy val video1: Video = Video(
    fileId = "video-1",
    fileUniqueId = "unique-video-1",
    width = 640,
    height = 480,
    duration = 15,
    thumb = Some(photoSize1),
    fileName = Some("video-1"),
    mimeType = Some("video/mp4"),
    fileSize = Some(54000)
  )

  lazy val videoNote1: VideoNote = VideoNote(
    fileId = "video-note-1",
    fileUniqueId = "unique-video-note-1",
    length = 4000,
    duration = 23,
    thumb = Some(photoSize2),
    fileSize = Some(64441)
  )

  lazy val voice1: Voice = Voice(
    fileId = "voice-1",
    fileUniqueId = "unique-voice-1",
    duration = 50,
    mimeType = Some("audio/mpeg"),
    fileSize = Some(1355)
  )

  lazy val animationMessage1: Message = Message
    .of(messageId = 411, date = instant1, chat = chat1)
    .withFrom(user1)
    .withAnimation(animation1)
    .withDocument(document1)

  lazy val audioMessage1: Message = Message
    .of(messageId = 412, date = instant2, chat = chat1)
    .withFrom(user1)
    .withAudio(audio1)

  lazy val contactMessage1: Message = Message
    .of(messageId = 413, date = instant3, chat = chat2)
    .withFrom(user1)
    .withContact(contact1)

  lazy val diceMessage1: Message = Message
    .of(messageId = 414, date = instant1, chat = chat1)
    .withFrom(user1)
    .withDice(dice1)

  lazy val documentMessage1: Message = Message
    .of(messageId = 415, date = instant3, chat = chat1)
    .withFrom(user1)
    .withDocument(document1)

  lazy val gameMessage1: Message = Message
    .of(messageId = 416, date = instant4, chat = chat1)
    .withFrom(user1)
    .withGame(game1)

  lazy val locationMessage1: Message = Message
    .of(messageId = 417, date = instant1, chat = chat1)
    .withFrom(user1)
    .withLocation(location1)

  lazy val photoMessage1: Message = Message
    .of(messageId = 418, date = instant1, chat = chat1)
    .withFrom(user1)
    .withPhoto(Seq(photoSize1, photoSize2))

  lazy val pollMessage1: Message = Message
    .of(messageId = 419, date = instant3, chat = chat1)
    .withFrom(user1)
    .withPoll(poll1)

  lazy val stickerMessage1: Message = Message
    .of(messageId = 420, date = instant3, chat = chat1)
    .withFrom(user1)
    .withSticker(sticker1)

  lazy val textMessage1: Message = Message
    .of(messageId = 421, date = instant1, chat = chat1)
    .withFrom(user1)
    .withEntities(Seq(messageEntity1))
    .withText("hello world")

  lazy val venueMessage1: Message = Message
    .of(messageId = 422, date = instant2, chat = chat1)
    .withFrom(user1)
    .withVenue(venue1)

  lazy val videoMessage1: Message = Message
    .of(messageId = 423, date = instant2, chat = chat1)
    .withFrom(user1)
    .withVideo(video1)

  lazy val videoNoteMessage1: Message = Message
    .of(messageId = 424, date = instant3, chat = chat1)
    .withFrom(user1)
    .withVideoNote(videoNote1)

  lazy val voiceMessage1: Message = Message
    .of(messageId = 425, date = instant1, chat = chat3)
    .withFrom(user1)
    .withVoice(voice1)

  lazy val newChatMembersMessage1: Message = Message
    .of(messageId = 426, date = instant2, chat = chat2)
    .withFrom(user1)
    .withNewChatMembers(Seq(user1))

  lazy val leftChatMemberMessage1: Message = Message
    .of(messageId = 427, date = instant1, chat = chat2)
    .withFrom(user1)
    .withLeftChatMember(user1)

  lazy val newChatTitleMessage1: Message = Message
    .of(messageId = 428, date = instant3, chat = chat1)
    .withFrom(user1)
    .withNewChatTitle("title one")

  lazy val allMessages: Set[Message] = Set(
    animationMessage1,
    audioMessage1,
    contactMessage1,
    diceMessage1,
    documentMessage1,
    gameMessage1,
    locationMessage1,
    photoMessage1,
    pollMessage1,
    stickerMessage1,
    textMessage1,
    venueMessage1,
    videoMessage1,
    videoNoteMessage1,
    voiceMessage1,
    newChatMembersMessage1,
    leftChatMemberMessage1,
    newChatTitleMessage1
  )

  lazy val animationMessageProjection: UpdateProjection = AnimationMessage(
    data = Data.of(animationMessage1).get,
    media = Media.of(animationMessage1),
    document = document1,
    animation = animation1
  )

  lazy val audioMessageProjection: UpdateProjection = AudioMessage(
    data = Data.of(audioMessage1).get,
    media = Media.of(audioMessage1),
    audio = audio1
  )

  lazy val contactMessageProjection: UpdateProjection = ContactMessage(
    data = Data.of(contactMessage1).get,
    contact = contact1
  )

  lazy val diceMessageProjection: UpdateProjection = DiceMessage(
    data = Data.of(diceMessage1).get,
    dice = dice1
  )

  lazy val documentMessageProjection: UpdateProjection = DocumentMessage(
    data = Data.of(documentMessage1).get,
    media = Media.of(documentMessage1),
    document = document1
  )

  lazy val gameMessageProjection: UpdateProjection = GameMessage(
    data = Data.of(gameMessage1).get,
    game = game1
  )

  lazy val locationMessageProjection: UpdateProjection = LocationMessage(
    data = Data.of(locationMessage1).get,
    location = location1
  )

  lazy val photoMessageProjection: UpdateProjection = PhotoMessage(
    data = Data.of(photoMessage1).get,
    media = Media.of(photoMessage1),
    photo = Seq(photoSize1, photoSize2)
  )

  lazy val pollMessageProjection: UpdateProjection = PollMessage(
    data = Data.of(pollMessage1).get,
    poll = poll1
  )

  lazy val stickerMessageProjection: UpdateProjection = StickerMessage(
    data = Data.of(stickerMessage1).get,
    sticker = sticker1
  )

  lazy val textMessageProjection: UpdateProjection = TextMessage(
    data = Data.of(textMessage1).get,
    entities = Seq(messageEntity1),
    text = "hello world"
  )

  lazy val venueMessageProjection: UpdateProjection = VenueMessage(
    data = Data.of(venueMessage1).get,
    venue = venue1
  )

  lazy val videoMessageProjection: UpdateProjection = VideoMessage(
    data = Data.of(videoMessage1).get,
    media = Media.of(videoMessage1),
    video = video1
  )

  lazy val videoNoteMessageProjection: UpdateProjection = VideoNoteMessage(
    data = Data.of(videoNoteMessage1).get,
    videoNote = videoNote1
  )

  lazy val voiceMessageProjection: UpdateProjection = VoiceMessage(
    data = Data.of(voiceMessage1).get,
    voice = voice1
  )

  lazy val newChatMembersProjection: UpdateProjection = NewChatMembersMessage(
    data = Data.of(newChatMembersMessage1).get,
    newMembers = Seq(user1)
  )

  lazy val leftChatMemberProjection: UpdateProjection = LeftChatMemberMessage(
    data = Data.of(leftChatMemberMessage1).get,
    member = user1
  )

  lazy val newChatTitleProjection: UpdateProjection = NewChatTitleMessage(
    data = Data.of(newChatTitleMessage1).get,
    newTitle = "title one"
  )
}
