package io.github.aalbul.zio.telegram.test

import io.github.aalbul.zio.telegram.command.FileDescriptor.{pathDescriptor, urlDescriptor}
import io.github.aalbul.zio.telegram.domain.*
import io.github.aalbul.zio.telegram.projection.*
import io.github.aalbul.zio.telegram.projection.message.*
import io.github.aalbul.zio.telegram.projection.message.MediaMessageProjection.Media
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data
import io.github.aalbul.zio.telegram.{domain, projection}

import java.time.Instant
import scala.concurrent.duration.DurationInt

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

  lazy val user2: User = User(
    id = 2,
    isBot = true,
    firstName = "Bot",
    lastName = Some("Woot"),
    username = Some("nemesis"),
    languageCode = Some("en"),
    canJoinGroups = Some(true),
    canReadAllGroupMessages = Some(true),
    supportsInlineQueries = Some(true)
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

  lazy val keyboardButtonPollType1: KeyboardButtonPollType = KeyboardButtonPollType(PollTypes.Quiz)

  lazy val keyboardButton1: KeyboardButton = KeyboardButton(
    text = "simple button",
    requestContact = Some(true),
    requestLocation = Some(false),
    requestPoll = Some(keyboardButtonPollType1),
    webApp = Some(webAppInfo1)
  )

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

  lazy val replyKeyboardMarkup1: ReplyKeyboardMarkup = ReplyKeyboardMarkup(
    keyboard = Seq(Seq(keyboardButton1)),
    resizeKeyboard = Some(true),
    oneTimeKeyboard = Some(false),
    inputFieldPlaceholder = Some("type you data here"),
    selective = Some(true)
  )

  lazy val replyKeyboardRemove1: ReplyKeyboardRemove = ReplyKeyboardRemove(
    removeKeyboard = true,
    selective = Some(true)
  )

  lazy val chat1: Chat = Chat.of(id = 81, `type` = ChatTypes.Supergroup)

  lazy val chat2: Chat = Chat.of(id = 82, `type` = ChatTypes.Private)

  lazy val chat3: Chat = Chat.of(id = 83, `type` = ChatTypes.Supergroup)

  lazy val chat4: Chat = Chat(
    id = 88,
    `type` = ChatTypes.Channel,
    title = Some("hello world"),
    username = Some("user-1"),
    firstName = Some("John"),
    lastName = Some("Wick"),
    isForum = Some(false),
    photo = Some(chatPhoto1),
    activeUsernames = Some(Seq("user-1", "user-2")),
    emojiStatusCustomEmojiId = Some("emoji-id-1"),
    bio = Some("some bio"),
    hasPrivateForwards = Some(true),
    hasRestrictedVoiceAndVideoMessages = Some(false),
    joinToSendMessages = Some(true),
    joinByRequest = Some(true),
    description = Some("some description"),
    inviteLink = Some("http://google.com/invite"),
    pinnedMessage = Some(message1),
    permissions = Some(chatPermissions1),
    slowModeDelay = Some(5),
    messageAutoDeleteTime = Some(10),
    hasProtectedContent = Some(false),
    stickerSetName = Some("sticker-set-1"),
    canSetStickerSet = Some(true),
    linkedChatId = Some(55),
    location = Some(chatLocation1)
  )

  lazy val instant1: Instant = Instant.parse("2022-06-12T10:15:30.00Z")

  lazy val instant2: Instant = Instant.parse("2022-06-12T10:41:32.00Z")

  lazy val instant3: Instant = Instant.parse("2022-06-13T06:15:11.00Z")

  private val instant4: Instant = Instant.parse("2022-06-13T06:18:10.00Z")

  lazy val messageId1: MessageId = MessageId(11)

  lazy val message1: Message = Message.of(messageId = 15, date = instant1, chat = chat1)

  lazy val message2: Message = Message.of(messageId = 16, date = instant2, chat = chat2)

  lazy val message3: Message = Message.of(messageId = 17, date = instant3, chat = chat3)

  lazy val message4: Message = Message.of(messageId = 18, date = instant4, chat = chat3)

  lazy val fullMessage1: Message = Message
    .of(messageId = 19, date = instant3, chat = chat2)
    .withMessageThreadId(22L)
    .withFrom(user1)
    .withSenderChat(chat2)
    .withForwardFrom(user2)
    .withForwardFromChat(chat3)
    .withForwardFromMessageId(81L)
    .withForwardSignature("signature one")
    .withForwardSenderName("sender one")
    .withForwardDate(instant3)
    .withIsTopicMessage(true)
    .withIsAutomaticForward(true)
    .withReplyToMessage(message2)
    .withViaBot(user2)
    .withEditDate(instant1)
    .withHasProtectedContent(true)
    .withMediaGroupId("media-group-1")
    .withAuthorSignature("signature-1")
    .withText("text-1")
    .withEntities(Seq(messageEntity1))
    .withAnimation(animation1)
    .withAudio(audio1)
    .withDocument(document1)
    .withPhoto(Seq(photoSize1, photoSize2))
    .withSticker(sticker1)
    .withVideo(video1)
    .withVideoNote(videoNote1)
    .withVoice(voice1)
    .withCaption("caption-1")
    .withCaptionEntities(Seq(messageEntity1))
    .withContact(contact1)
    .withDice(dice1)
    .withGame(game1)
    .withPoll(poll1)
    .withVenue(venue1)
    .withLocation(location1)
    .withNewChatMembers(Seq(user1))
    .withLeftChatMember(user2)
    .withNewChatTitle("chat-title-1")
    .withNewChatPhoto(Seq(photoSize2))
    .withDeleteChatPhoto(true)
    .withGroupChatCreated(true)
    .withSupergroupChatCreated(true)
    .withChannelChatCreated(true)
    .withMessageAutoDeleteTimerChanged(messageAutoDeleteTimerChanged1)
    .withMigrateToChatId(2)
    .withMigrateFromChatId(5)
    .withPinnedMessage(message4)
    .withInvoice(invoice1)
    .withSuccessfulPayment(successfulPayment1)
    .withConnectedWebsite("http://website.com")
    .withPassportData(passportData1)
    .withProximityAlertTriggered(proximityAlertTriggered1)
    .withForumTopicCreated(forumTopicCreated1)
    .withForumTopicClosed(forumTopicClosed1)
    .withForumTopicReopened(forumTopicReopened1)
    .withVideoChatScheduled(videoChatScheduled1)
    .withVideoChatStarted(videoChatStarted1)
    .withVideoChatEnded(videoChatEnded1)
    .withVideoChatParticipantsInvited(videoChatParticipantsInvited1)
    .withWebAppData(webAppData1)
    .withReplyMarkup(inlineKeyboardMarkup1)

  lazy val inputMediaAnimation1: InputMedia = InputMediaAnimation
    .of(pathDescriptor("/tmp/one.gif"))
    .withThumb(urlDescriptor("https://google.com/animation_thumb.jpg"))
    .withCaption("my gif")
    .withParseMode(ParseModes.MarkdownV2)
    .withCaptionEntities(Seq(messageEntity1))
    .withWidth(1024)
    .withHeight(769)
    .withDuration(346)

  lazy val inputMediaAudio1: InputMedia = InputMediaAudio
    .of(pathDescriptor("/tmp/one.mp3"))
    .withThumb(pathDescriptor("/tmp/song_thumb.jpg"))
    .withCaption("my song")
    .withParseMode(ParseModes.HTML)
    .withCaptionEntities(Seq(messageEntity1))
    .withDuration(346)
    .withPerformer("Me")
    .withTitle("Best song ever")

  lazy val inputMediaDocument1: InputMedia = InputMediaDocument
    .of(pathDescriptor("/tmp/one.pdf"))
    .withThumb(pathDescriptor("/tmp/document_thumb.jpg"))
    .withCaption("my document")
    .withParseMode(ParseModes.Markdown)
    .withCaptionEntities(Seq(messageEntity1))
    .withDisableContentTypeDetection(true)

  lazy val inputMediaPhoto1: InputMedia = InputMediaPhoto
    .of(pathDescriptor("/tmp/one.jpg"))
    .withThumb(pathDescriptor("/tmp/photo_thumb.jpg"))
    .withCaption("my photo")
    .withParseMode(ParseModes.Markdown)
    .withCaptionEntities(Seq(messageEntity1))

  lazy val inputMediaVideo1: InputMedia = InputMediaVideo
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
    canPinMessages = Some(true),
    canManageTopics = Some(true)
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
    mimeType = Some("application/document"),
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

  lazy val pollOption1: PollOption = PollOption(text = "Apple", voterCount = 10)
  lazy val pollOption2: PollOption = PollOption(text = "Peer", voterCount = 3)
  lazy val pollOption3: PollOption = PollOption(text = "Watermelon", voterCount = 8)

  lazy val poll1: Poll = Poll(
    id = "poll-1",
    question = "Favorite fruit",
    options = Seq(pollOption1, pollOption2, pollOption3),
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

  lazy val pollAnswer1: PollAnswer = PollAnswer(
    pollId = poll1.id,
    user = user2,
    optionIds = Seq(2, 3)
  )

  lazy val chatMember1: ChatMember = ChatMemberOwner(
    user = user1,
    isAnonymous = false,
    customTitle = Some("title-1")
  )

  lazy val chatMember2: ChatMember = ChatMemberLeft(user = user2)

  lazy val chatMember3: ChatMember = ChatMemberAdministrator(
    user = user1,
    canBeEdited = true,
    isAnonymous = false,
    canManageChat = true,
    canDeleteMessages = false,
    canManageVideoChats = true,
    canRestrictMembers = false,
    canPromoteMembers = false,
    canChangeInfo = true,
    canInviteUsers = false,
    canPostMessages = Some(true),
    canEditMessages = Some(false),
    canPinMessages = Some(true),
    canManageTopics = Some(false),
    customerTitle = Some("custom title")
  )

  lazy val chatMember4: ChatMember = ChatMemberBanned(
    user = user1,
    untilDate = 30000
  )

  lazy val chatMember5: ChatMember = ChatMemberMember(user = user1)

  lazy val chatMember6: ChatMember = ChatMemberRestricted(
    user = user1,
    isMember = true,
    canChangeInfo = false,
    canInviteUsers = true,
    canPinMembers = false,
    canSendMessages = true,
    canSendMediaMessages = false,
    canSendPolls = false,
    canSendOtherMessages = true,
    canAddWebPagePreviews = true,
    untilDate = 50
  )

  lazy val chatInviteLink1: ChatInviteLink = ChatInviteLink(
    inviteLink = "http://google.com/invite",
    creator = user1,
    createsJoinRequest = true,
    isPrimary = true,
    isRevoked = false,
    name = Some("invite-1"),
    expirationDate = Some(instant4),
    memberLimit = Some(22),
    pendingJoinRequestCount = Option(6)
  )

  lazy val chatMemberUpdated1: ChatMemberUpdated = ChatMemberUpdated(
    chat = chat1,
    from = user1,
    date = instant2,
    oldChatMember = chatMember1,
    newChatMember = chatMember2,
    inviteLink = Option(chatInviteLink1)
  )

  lazy val chatJoinRequest1: ChatJoinRequest = ChatJoinRequest(
    chat = chat1,
    from = user2,
    date = instant4,
    bio = Some("bio-1"),
    inviteLink = Some(chatInviteLink1)
  )

  lazy val maskPosition1: MaskPosition = MaskPosition(
    point = MaskPointTypes.Forehead,
    xShift = 10.2,
    yShift = 5.4,
    scale = 1.3
  )

  lazy val sticker1: Sticker = Sticker(
    fileId = "sticker-1",
    fileUniqueId = "unique-sticker-1",
    `type` = StickerTypes.CustomEmoji,
    width = 80,
    height = 50,
    isAnimated = true,
    isVideo = false,
    thumb = Some(photoSize1),
    emoji = Some("🎰"),
    setName = Some("set-1"),
    premiumAnimation = Some(file1),
    maskPosition = Some(maskPosition1),
    customEmojiId = Some("custom-emoji-id-1"),
    fileSize = Some(500)
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

  lazy val invoice1: Invoice = Invoice(
    title = "Shopping",
    description = "Your invoice",
    startParameter = "param-1",
    currency = "EUR",
    totalAmount = 53
  )

  lazy val shippingAddress1: ShippingAddress = ShippingAddress(
    countryCode = "NL",
    state = "Utrecht",
    city = "Amersfoort",
    streetLine1 = "Some Street 1",
    streetLine2 = "Other",
    postCode = "3821KL"
  )

  lazy val orderInfo1: OrderInfo = OrderInfo
    .of()
    .withName("John Wick")
    .withEmail("jwick@google.com")
    .withPhoneNumber("+312345689")
    .withShippingAddress(shippingAddress1)

  lazy val successfulPayment1: SuccessfulPayment = SuccessfulPayment
    .of(
      currency = "EUR",
      totalAmount = 53,
      invoicePayload = "payload - 1",
      telegramPaymentChargeId = "charge - 1",
      providerPaymentChargeId = "provider payment - 1"
    )
    .withOrderInfo(orderInfo1)
    .withShippingOptionId("option - 1")

  lazy val encryptedCredentials1: EncryptedCredentials = EncryptedCredentials(
    data = "data-1",
    hash = "hash-1",
    secret = "secret-1"
  )

  lazy val passportFile1: PassportFile = PassportFile(
    fileId = "file-1",
    fileUniqueId = "unique-file-1",
    fileSize = 200,
    fileDate = instant1
  )

  lazy val passportFile2: PassportFile = PassportFile(
    fileId = "file-2",
    fileUniqueId = "unique-file-2",
    fileSize = 150,
    fileDate = instant2
  )

  lazy val passportFile3: PassportFile = PassportFile(
    fileId = "file-3",
    fileUniqueId = "unique-file-3",
    fileSize = 125,
    fileDate = instant3
  )

  lazy val passportFile4: PassportFile = PassportFile(
    fileId = "file-4",
    fileUniqueId = "unique-file-4",
    fileSize = 63,
    fileDate = instant4
  )

  lazy val passportFile5: PassportFile = PassportFile(
    fileId = "file-5",
    fileUniqueId = "unique-file-5",
    fileSize = 11,
    fileDate = instant2
  )

  lazy val encryptedPassportElement1: EncryptedPassportElement = EncryptedPassportElement(
    `type` = PassportElementTypes.Address,
    data = Some("address-1"),
    phoneNumber = Some("+31630911234"),
    email = Some("email@gmail.com"),
    files = Some(Seq(passportFile1)),
    frontSide = Some(passportFile2),
    reverseSide = Some(passportFile3),
    selfie = Some(passportFile4),
    translation = Some(Seq(passportFile5)),
    hash = "hash-1"
  )

  lazy val passportData1: PassportData = PassportData(
    data = Seq(encryptedPassportElement1),
    credentials = encryptedCredentials1
  )

  lazy val proximityAlertTriggered1: ProximityAlertTriggered = ProximityAlertTriggered(
    traveler = user1,
    watcher = user2,
    distance = 20
  )

  lazy val videoChatScheduled1: VideoChatScheduled = VideoChatScheduled(startDate = instant4)

  lazy val videoChatStarted1: VideoChatStarted = VideoChatStarted()

  lazy val videoChatEnded1: VideoChatEnded = VideoChatEnded(duration = 10.minutes)

  lazy val videoChatParticipantsInvited1: VideoChatParticipantsInvited = VideoChatParticipantsInvited(
    users = Seq(user2, user1)
  )

  lazy val webAppData1: WebAppData = WebAppData(
    data = "data-1",
    buttonText = "button one"
  )

  lazy val forumTopicCreated1: ForumTopicCreated = ForumTopicCreated
    .of(name = "topic-1", iconColor = 255)
    .withIconCustomEmojiId("emoji-1")

  lazy val forumTopicClosed1: ForumTopicClosed = ForumTopicClosed.of()

  lazy val forumTopicReopened1: ForumTopicReopened = ForumTopicReopened.of()

  lazy val inlineQuery1: InlineQuery = InlineQuery(
    id = "query-1",
    from = user1,
    query = "test",
    offset = "1",
    chatType = Some(ChatTypes.Sender),
    location = Some(location1)
  )

  lazy val chatPhoto1: ChatPhoto = ChatPhoto(
    smallFileId = "small-file-id-1",
    smallFileUniqueId = "unique-small-file-id-1",
    bigFileId = "big-file-id-1",
    bigFileUniqueId = "unique-big-file-id-1"
  )

  lazy val chatLocation1: ChatLocation = ChatLocation(
    location = location1,
    address = "Jachthaven 1, 2172 JX Sassenheim"
  )

  lazy val chosenInlineResult1: domain.ChosenInlineResult = domain.ChosenInlineResult(
    resultId = "result-1",
    from = user1,
    location = Some(location1),
    inlineMessageId = Some("message-1"),
    query = "query-1"
  )

  lazy val callbackQuery1: CallbackQuery = CallbackQuery(
    id = "callback-1",
    from = user1,
    message = Some(message1),
    inlineMessageId = Some("inline-message-1"),
    chatInstance = "chat-1",
    data = Some("data-1"),
    gameShortName = Some("game-short-name")
  )

  lazy val shippingQuery1: ShippingQuery = ShippingQuery(
    id = "shipping-query-1",
    from = user1,
    invoicePayload = "payload-1",
    shippingAddress = shippingAddress1
  )

  lazy val preCheckoutQuery1: PreCheckoutQuery = PreCheckoutQuery(
    id = "pre-checkout-query-1",
    from = user1,
    currency = "USD",
    totalAmount = 20,
    invoicePayload = "payload-1",
    shippingOptionId = Some("option-id-1"),
    orderInfo = Some(orderInfo1)
  )

  lazy val messageAutoDeleteTimerChanged1: MessageAutoDeleteTimerChanged =
    MessageAutoDeleteTimerChanged(messageAutoDeleteTime = 30)

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

  lazy val textMessage2: Message = textMessage1.withText("changed text")

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

  lazy val newChatPhotoMessage1: Message = Message
    .of(messageId = 429, date = instant1, chat = chat1)
    .withFrom(user1)
    .withNewChatPhoto(Seq(photoSize2, photoSize1))

  lazy val deleteChatPhotoMessage1: Message = Message
    .of(messageId = 430, date = instant2, chat = chat1)
    .withFrom(user1)
    .withDeleteChatPhoto(true)

  lazy val groupChatCreatedMessage1: Message = Message
    .of(messageId = 431, date = instant4, chat = chat1)
    .withFrom(user1)
    .withGroupChatCreated(true)

  lazy val supergroupChatCreatedMessage1: Message = Message
    .of(messageId = 432, date = instant1, chat = chat1)
    .withFrom(user1)
    .withSupergroupChatCreated(true)

  lazy val channelChatCreatedMessage1: Message = Message
    .of(messageId = 433, date = instant2, chat = chat1)
    .withFrom(user1)
    .withChannelChatCreated(true)

  lazy val messageAutoDeleteTimerChangedMessage1: Message = Message
    .of(messageId = 434, date = instant3, chat = chat1)
    .withFrom(user1)
    .withMessageAutoDeleteTimerChanged(messageAutoDeleteTimerChanged1)

  lazy val migrateToChatIdMessage1: Message = Message
    .of(messageId = 435, date = instant1, chat = chat1)
    .withFrom(user1)
    .withMigrateToChatId(31)

  lazy val migrateFromChatIdMessage1: Message = Message
    .of(messageId = 436, date = instant4, chat = chat1)
    .withFrom(user1)
    .withMigrateFromChatId(15)

  lazy val pinnedMessageMessage1: Message = Message
    .of(messageId = 437, date = instant2, chat = chat1)
    .withFrom(user1)
    .withPinnedMessage(textMessage1)

  lazy val invoiceMessage1: Message = Message
    .of(messageId = 438, date = instant1, chat = chat1)
    .withFrom(user1)
    .withInvoice(invoice1)

  lazy val successfulPaymentMessage1: Message = Message
    .of(messageId = 439, date = instant3, chat = chat1)
    .withFrom(user1)
    .withSuccessfulPayment(successfulPayment1)

  lazy val passportDataMessage1: Message = Message
    .of(messageId = 440, date = instant1, chat = chat2)
    .withFrom(user1)
    .withPassportData(passportData1)

  lazy val proximityAlertTriggeredMessage1: Message = Message
    .of(messageId = 441, date = instant3, chat = chat2)
    .withFrom(user1)
    .withProximityAlertTriggered(proximityAlertTriggered1)

  lazy val videoChatScheduledMessage1: Message = Message
    .of(messageId = 442, date = instant2, chat = chat1)
    .withFrom(user1)
    .withVideoChatScheduled(videoChatScheduled1)

  lazy val videoChatStartedMessage1: Message = Message
    .of(messageId = 443, date = instant1, chat = chat1)
    .withFrom(user1)
    .withVideoChatStarted(videoChatStarted1)

  lazy val videoChatEndedMessage1: Message = Message
    .of(messageId = 444, date = instant2, chat = chat1)
    .withFrom(user1)
    .withVideoChatEnded(videoChatEnded1)

  lazy val videoChatParticipantsInvitedMessage1: Message = Message
    .of(messageId = 445, date = instant3, chat = chat1)
    .withFrom(user1)
    .withVideoChatParticipantsInvited(videoChatParticipantsInvited1)

  lazy val webAppDataMessage1: Message = Message
    .of(messageId = 446, date = instant2, chat = chat1)
    .withFrom(user1)
    .withWebAppData(webAppData1)

  lazy val forumTopicCreatedMessage1: Message = Message
    .of(messageId = 447, date = instant1, chat = chat1)
    .withFrom(user1)
    .withForumTopicCreated(forumTopicCreated1)

  lazy val forumTopicClosedMessage1: Message = Message
    .of(messageId = 448, date = instant2, chat = chat1)
    .withFrom(user1)
    .withForumTopicClosed(forumTopicClosed1)

  lazy val forumTopicReopenedMessage1: Message = Message
    .of(messageId = 449, date = instant4, chat = chat1)
    .withFrom(user1)
    .withForumTopicReopened(forumTopicReopened1)

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
    newChatTitleMessage1,
    newChatPhotoMessage1,
    deleteChatPhotoMessage1,
    groupChatCreatedMessage1,
    supergroupChatCreatedMessage1,
    channelChatCreatedMessage1,
    messageAutoDeleteTimerChangedMessage1,
    migrateToChatIdMessage1,
    migrateFromChatIdMessage1,
    pinnedMessageMessage1,
    invoiceMessage1,
    successfulPaymentMessage1,
    passportDataMessage1,
    proximityAlertTriggeredMessage1,
    videoChatScheduledMessage1,
    videoChatStartedMessage1,
    videoChatEndedMessage1,
    videoChatParticipantsInvitedMessage1,
    webAppDataMessage1,
    forumTopicCreatedMessage1,
    forumTopicClosedMessage1,
    forumTopicReopenedMessage1
  )

  lazy val updateTextMessage1: Update = Update.of(updateId = 66).withMessage(textMessage1)
  lazy val updateVoiceMessage1: Update = Update.of(updateId = 67).withMessage(voiceMessage1)
  lazy val updateAudioMessage1: Update = Update.of(updateId = 68).withMessage(audioMessage1)
  lazy val updateVideoMessage1: Update = Update.of(updateId = 69).withMessage(videoMessage1)

  lazy val updateEditedTextMessage: Update = Update.of(updateId = 70).withEditedMessage(textMessage2)
  lazy val channelPostAudioMessage: Update = Update.of(updateId = 71).withChannelPost(audioMessage1)
  lazy val editedChannelPostMessage: Update = Update.of(updateId = 72).withEditedChannelPost(voiceMessage1)
  lazy val inlineQueryMessage: Update = Update.of(updateId = 73).withInlineQuery(inlineQuery1)
  lazy val chosenInlineResultMessage: Update = Update.of(updateId = 74).withChosenInlineResult(chosenInlineResult1)
  lazy val callbackQueryMessage: Update = Update.of(updateId = 75).withCallbackQuery(callbackQuery1)
  lazy val shippingQueryMessage: Update = Update.of(updateId = 76).withShippingQuery(shippingQuery1)
  lazy val preCheckoutQueryMessage: Update = Update.of(updateId = 77).withPreCheckoutQuery(preCheckoutQuery1)
  lazy val newPollMessage: Update = Update.of(updateId = 78).withPoll(poll1)
  lazy val newPollAnswerMessage: Update = Update.of(updateId = 79).withPollAnswer(pollAnswer1)
  lazy val updatedMyChatMemberMessage: Update = Update.of(updateId = 80).withMyChatMember(chatMemberUpdated1)
  lazy val updatedChatMemberMessage: Update = Update.of(updateId = 81).withChatMember(chatMemberUpdated1)
  lazy val newChatJoinRequestMessage: Update = Update.of(updateId = 82).withChatJoinRequest(chatJoinRequest1)

  lazy val allUpdates: Set[Update] = allMessages.map(message => Update.of(1).withMessage(message)) ++ Set(
    updateEditedTextMessage,
    channelPostAudioMessage,
    editedChannelPostMessage,
    inlineQueryMessage,
    chosenInlineResultMessage,
    callbackQueryMessage,
    shippingQueryMessage,
    preCheckoutQueryMessage,
    newPollMessage,
    newPollAnswerMessage,
    updatedChatMemberMessage,
    updatedMyChatMemberMessage,
    newChatJoinRequestMessage
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

  lazy val newChatPhotoProjection: UpdateProjection = NewChatPhotoMessage(
    data = Data.of(newChatPhotoMessage1).get,
    newPhoto = Seq(photoSize2, photoSize1)
  )

  lazy val deleteChatPhotoProjection: UpdateProjection = DeleteChatPhotoMessage(
    data = Data.of(deleteChatPhotoMessage1).get
  )

  lazy val groupChatCreatedProjection: UpdateProjection = GroupChatCreatedMessage(
    data = Data.of(groupChatCreatedMessage1).get
  )

  lazy val supergroupChatCreatedProjection: UpdateProjection = SupergroupChatCreatedMessage(
    data = Data.of(supergroupChatCreatedMessage1).get
  )

  lazy val channelChatCreatedProjection: UpdateProjection = ChannelChatCreatedMessage(
    data = Data.of(channelChatCreatedMessage1).get
  )

  lazy val messageAutoDeleteTimerChangedProjection: UpdateProjection = MessageAutoDeleteTimerChangedMessage(
    data = Data.of(messageAutoDeleteTimerChangedMessage1).get,
    change = messageAutoDeleteTimerChanged1
  )

  lazy val migrateToChatIdProjection: UpdateProjection = MigrateToChatIdMessage(
    data = Data.of(migrateToChatIdMessage1).get,
    chatId = 31
  )

  lazy val migrateFromChatIdProjection: UpdateProjection = MigrateFromChatIdMessage(
    data = Data.of(migrateFromChatIdMessage1).get,
    chatId = 15
  )

  lazy val pinnedMessageProjection: UpdateProjection = PinnedMessageMessage(
    data = Data.of(pinnedMessageMessage1).get,
    message = textMessageProjection
  )

  lazy val invoiceMessageProjection: UpdateProjection = InvoiceMessage(
    data = Data.of(invoiceMessage1).get,
    invoice = invoice1
  )

  lazy val successfulPaymentProjection: UpdateProjection = SuccessfulPaymentMessage(
    data = Data.of(successfulPaymentMessage1).get,
    payment = successfulPayment1
  )

  lazy val passportDataProjection: UpdateProjection = PassportDataMessage(
    data = Data.of(passportDataMessage1).get,
    passportData = passportData1
  )

  lazy val proximityAlertTriggeredProjection: UpdateProjection = ProximityAlertTriggeredMessage(
    data = Data.of(proximityAlertTriggeredMessage1).get,
    event = proximityAlertTriggered1
  )

  lazy val videoChatScheduledProjection: UpdateProjection = VideoChatScheduledMessage(
    data = Data.of(videoChatScheduledMessage1).get,
    event = videoChatScheduled1
  )

  lazy val videoChatStartedProjection: UpdateProjection = VideoChatStartedMessage(
    data = Data.of(videoChatStartedMessage1).get
  )

  lazy val videoChatEndedProjection: UpdateProjection = VideoChatEndedMessage(
    data = Data.of(videoChatEndedMessage1).get,
    event = videoChatEnded1
  )

  lazy val videoChatParticipantsInvitedProjection: UpdateProjection = VideoChatParticipantsInvitedMessage(
    data = Data.of(videoChatParticipantsInvitedMessage1).get,
    event = videoChatParticipantsInvited1
  )

  lazy val webAppDataProjection: UpdateProjection = WebAppDataMessage(
    data = Data.of(webAppDataMessage1).get,
    webAppData = webAppData1
  )

  lazy val editedTextMessageProjection: UpdateProjection = EditedMessage(
    TextMessage(
      data = Data.of(textMessage2).get,
      entities = Seq(messageEntity1),
      text = "changed text"
    )
  )

  lazy val channelPostMessageProjection: UpdateProjection = ChannelPost(
    AudioMessage(
      data = Data.of(audioMessage1).get,
      media = Media.of(audioMessage1),
      audio = audio1
    )
  )

  lazy val editedChannelPostProjection: UpdateProjection = EditedChannelPost(
    VoiceMessage(
      data = Data.of(voiceMessage1).get,
      voice = voice1
    )
  )

  lazy val chosenInlineResultProjection: UpdateProjection = projection.ChosenInlineResult(chosenInlineResult1)

  lazy val inlineQueryProjection: UpdateProjection = NewInlineQuery(inlineQuery1)

  lazy val newCallbackQueryProjection: UpdateProjection = NewCallbackQuery(callbackQuery1)

  lazy val newShippingQueryProjection: UpdateProjection = NewShippingQuery(shippingQuery1)

  lazy val newPreCheckoutQueryProjection: UpdateProjection = NewPreCheckoutQuery(preCheckoutQuery1)

  lazy val newPollProjection: UpdateProjection = NewPoll(poll1)

  lazy val newPollAnswerProjection: UpdateProjection = NewPollAnswer(pollAnswer1)

  lazy val updatedMyChatMemberProjection: UpdateProjection = UpdatedMyChatMember(chatMemberUpdated1)

  lazy val updatedChatMemberProjection: UpdateProjection = UpdatedChatMember(chatMemberUpdated1)

  lazy val newChatJoinRequestProjection: UpdateProjection = NewChatJoinRequest(chatJoinRequest1)

  lazy val forumTopicCreatedProjection: UpdateProjection =
    ForumTopicCreatedMessage(Data.of(forumTopicCreatedMessage1).get, forumTopicCreated1)

  lazy val forumTopicClosedProjection: UpdateProjection =
    ForumTopicClosedMessage(Data.of(forumTopicClosedMessage1).get, forumTopicClosed1)

  lazy val forumTopicReopenedProjection: UpdateProjection =
    ForumTopicReopenedMessage(Data.of(forumTopicReopenedMessage1).get, forumTopicReopened1)
}
