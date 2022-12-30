package io.github.aalbul.zio.telegram.test

import io.github.aalbul.zio.telegram.command.FileDescriptor.{pathDescriptor, urlDescriptor}
import io.github.aalbul.zio.telegram.domain.*
import io.github.aalbul.zio.telegram.engine.{ApiResponse, FailureApiResponse, SuccessApiResponse}
import io.github.aalbul.zio.telegram.projection.*
import io.github.aalbul.zio.telegram.projection.message.*
import io.github.aalbul.zio.telegram.projection.message.MediaMessageProjection.Media
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data
import io.github.aalbul.zio.telegram.{domain, projection}

import java.time.Instant
import scala.concurrent.duration.DurationInt

trait Builders {
  lazy val user1: User = User
    .of(
      id = 1,
      isBot = false,
      firstName = "John"
    )
    .withLastName("Wane")
    .withUsername("jwane")
    .withLanguageCode("en")
    .withIsPremium(true)
    .withAddedToAttachmentMenu(true)
    .withCanJoinGroups(true)
    .withCanReadAllGroupMessages(true)
    .withSupportsInlineQueries(false)

  lazy val user2: User = User
    .of(
      id = 2,
      isBot = true,
      firstName = "Bot"
    )
    .withLastName("Woot")
    .withUsername("nemesis")
    .withLanguageCode("en")
    .withIsPremium(false)
    .withAddedToAttachmentMenu(true)
    .withCanJoinGroups(true)
    .withCanReadAllGroupMessages(true)
    .withSupportsInlineQueries(true)

  lazy val userProfilePhotos1: UserProfilePhotos = UserProfilePhotos.of(
    totalCount = 2,
    photos = Seq(Seq(photoSize1), Seq(photoSize2))
  )

  lazy val messageEntity1: MessageEntity = MessageEntity
    .of(
      `type` = MessageEntityType.Pre,
      offset = 10,
      length = 500
    )
    .withUrl("https://someurl.com")
    .withUser(user1)
    .withLanguage("js")
    .withCustomEmojiId("emoji1")

  lazy val callbackGame1: CallbackGame = CallbackGame.of

  lazy val webAppInfo1: WebAppInfo = WebAppInfo.of(url = "https://webapp.org")

  lazy val forceReplyMarkup1: ForceReply = ForceReply
    .of(forceReply = true)
    .withInputFieldPlaceholder("some text")
    .withSelective(true)

  lazy val loginUrl1: LoginUrl = LoginUrl
    .of("https://google.com/login")
    .withBotUsername("bot-1")
    .withForwardText("forward text")
    .withRequestWriteAccess(true)

  lazy val keyboardButtonPollType1: KeyboardButtonPollType = KeyboardButtonPollType
    .of(PollType.Quiz)

  lazy val keyboardButton1: KeyboardButton = KeyboardButton
    .of(text = "simple button")
    .withRequestContact(true)
    .withRequestLocation(false)
    .withRequestPoll(keyboardButtonPollType1)
    .withWebApp(webAppInfo1)

  lazy val inlineKeyboardButton1: InlineKeyboardButton = InlineKeyboardButton
    .of(text = "some button 1")
    .withUrl("https://google.com/url1")
    .withCallbackData("some data")
    .withWebApp(webAppInfo1)
    .withLoginUrl(loginUrl1)
    .withSwitchInlineQuery("inline query 1")
    .withSwitchInlineQueryCurrentChat("inline query current chat 1")
    .withCallbackGame(callbackGame1)
    .withPay(true)

  lazy val inlineKeyboardButton2: InlineKeyboardButton = InlineKeyboardButton
    .of(text = "some button 2")
    .withUrl("https://google.com/url2")
    .withCallbackData("another data")
    .withLoginUrl(loginUrl1)
    .withSwitchInlineQuery("inline query 2")
    .withSwitchInlineQueryCurrentChat("inline query current chat 2")
    .withPay(false)

  lazy val inlineKeyboardMarkup1: InlineKeyboardMarkup = InlineKeyboardMarkup
    .of(Seq(Seq(inlineKeyboardButton1), Seq(inlineKeyboardButton2)))

  lazy val replyKeyboardMarkup1: ReplyKeyboardMarkup = ReplyKeyboardMarkup
    .of(keyboard = Seq(Seq(keyboardButton1)))
    .withResizeKeyboard(true)
    .withOneTimeKeyboard(false)
    .withInputFieldPlaceholder("type you data here")
    .withSelective(true)

  lazy val replyKeyboardRemove1: ReplyKeyboardRemove = ReplyKeyboardRemove
    .of(removeKeyboard = true)
    .withSelective(true)

  lazy val sentWebAppMessage1: SentWebAppMessage = SentWebAppMessage.of
    .withInlineMessageId("inline-message-id")

  lazy val chat1: Chat = Chat.of(id = 81, `type` = ChatType.Supergroup)

  lazy val chat2: Chat = Chat.of(id = 82, `type` = ChatType.Private)

  lazy val chat3: Chat = Chat.of(id = 83, `type` = ChatType.Supergroup)

  lazy val chat4: Chat = Chat
    .of(
      id = 88,
      `type` = ChatType.Channel
    )
    .withTitle("hello world")
    .withUsername("user-1")
    .withFirstName("John")
    .withLastName("Wick")
    .withIsForum(false)
    .withPhoto(chatPhoto1)
    .withActiveUsernames(Seq("user-1", "user-2"))
    .withEmojiStatusCustomEmojiId("emoji-id-1")
    .withBio("some bio")
    .withHasPrivateForwards(true)
    .withHasRestrictedVoiceAndVideoMessages(false)
    .withJoinToSendMessages(true)
    .withJoinByRequest(true)
    .withDescription("some description")
    .withInviteLink("http://google.com/invite")
    .withPinnedMessage(message1)
    .withPermissions(chatPermissions1)
    .withSlowModeDelay(5)
    .withMessageAutoDeleteTime(10)
    .withHasProtectedContent(false)
    .withStickerSetName("sticker-set-1")
    .withCanSetStickerSet(true)
    .withLinkedChatId(55)
    .withLocation(chatLocation1)

  lazy val instant1: Instant = Instant.parse("2022-06-12T10:15:30.00Z")

  lazy val instant2: Instant = Instant.parse("2022-06-12T10:41:32.00Z")

  lazy val instant3: Instant = Instant.parse("2022-06-13T06:15:11.00Z")

  private val instant4: Instant = Instant.parse("2022-06-13T06:18:10.00Z")

  lazy val messageId1: MessageId = MessageId.of(11)

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

  lazy val inputMessageContent1: InputMessageContent = InputTextMessageContent
    .of("text message")
    .withParseMode(ParseMode.HTML)
    .withEntities(Seq(messageEntity1))
    .withDisableWebPagePreview(true)

  lazy val inputMessageContent2: InputMessageContent = InputLocationMessageContent
    .of(latitude = 53.12, longitude = 25.51)
    .withHorizontalAccuracy(13.23)
    .withLivePeriod(2.minutes)
    .withHeading(57)
    .withProximityAlertRadius(13)

  lazy val inputMessageContent3: InputMessageContent = InputVenueMessageContent
    .of(longitude = 52.22, latitude = 4.55, title = "NEMO Science Museum", address = "Oosterdok 2, 1011 VX Amsterdam")
    .withFoursquareId("fsq1")
    .withFoursquareType("fsq-t-1")
    .withGooglePlaceId("gpi1")
    .withGooglePlaceType("gp-t-1")

  lazy val inputMessageContent4: InputMessageContent = InputContactMessageContent
    .of(phoneNumber = "+31680822212", firstName = "John")
    .withLastName("Wick")
    .withVcard("vcard")

  lazy val inputMessageContent5: InputMessageContent = InputInvoiceMessageContent
    .of(
      title = "invoice one",
      description = "my very first invoice",
      payload = "invoice payload",
      providerToken = "provider-token-1",
      currency = "USD",
      prices = Seq(labeledPrice1)
    )
    .withMaxTipAmount(10)
    .withSuggestedTipAmounts(Seq(5, 8, 10))
    .withProviderData("some provider data")
    .withPhotoUrl("https://google.com/photo.jpg")
    .withPhotoSize(231)
    .withPhotoWidth(640)
    .withPhotoHeight(480)
    .withNeedName(true)
    .withNeedPhoneNumber(true)
    .withNeedEmail(true)
    .withNeedShippingAddress(true)
    .withSendPhoneNumberToProvider(false)
    .withSendEmailToProvider(true)
    .withIsFlexible(true)

  lazy val inlineQueryResult1: InlineQueryResult = InlineQueryResultCachedAudio
    .of(id = "22", audioFileId = "audio-file-1")
    .withCaption("my audio file")
    .withParseMode(ParseMode.Markdown)
    .withCaptionEntities(Seq(messageEntity1))
    .withReplyMarkup(inlineKeyboardMarkup1)
    .withInputMessageContent(inputMessageContent3)

  lazy val inlineQueryResult2: InlineQueryResult = InlineQueryResultCachedDocument
    .of(id = "23", title = "some document", documentFileId = "document-file-1")
    .withDescription("document description")
    .withCaption("document caption")
    .withParseMode(ParseMode.Markdown)
    .withCaptionEntities(Seq(messageEntity1))
    .withReplyMarkup(inlineKeyboardMarkup1)
    .withInputMessageContent(inputMessageContent3)

  lazy val inlineQueryResult3: InlineQueryResult = InlineQueryResultCachedGif
    .of(id = "24", gifFileId = "gif-file-1")
    .withTitle("gif title")
    .withCaption("gif caption")
    .withParseMode(ParseMode.Markdown)
    .withCaptionEntities(Seq(messageEntity1))
    .withReplyMarkup(inlineKeyboardMarkup1)
    .withInputMessageContent(inputMessageContent1)

  lazy val inlineQueryResult4: InlineQueryResult = InlineQueryResultCachedMpeg4Gif
    .of(id = "25", mpeg4FileId = "mpeg-4-file-1")
    .withTitle("mpeg 4 title")
    .withCaption("mpeg 4 caption")
    .withParseMode(ParseMode.Markdown)
    .withCaptionEntities(Seq(messageEntity1))
    .withReplyMarkup(inlineKeyboardMarkup1)
    .withInputMessageContent(inputMessageContent1)

  lazy val inlineQueryResult5: InlineQueryResult = InlineQueryResultCachedPhoto
    .of(id = "27", photoFileId = "photo-file-1")
    .withTitle("photo title")
    .withDescription("photo description")
    .withCaption("photo caption")
    .withParseMode(ParseMode.Markdown)
    .withCaptionEntities(Seq(messageEntity1))
    .withReplyMarkup(inlineKeyboardMarkup1)
    .withInputMessageContent(inputMessageContent1)

  lazy val inlineQueryResult6: InlineQueryResult = InlineQueryResultCachedSticker
    .of(id = "28", stickerFileId = "sticker-file-1")
    .withReplyMarkup(inlineKeyboardMarkup1)
    .withInputMessageContent(inputMessageContent1)

  lazy val inlineQueryResult7: InlineQueryResult = InlineQueryResultCachedVideo
    .of(id = "29", videoFileId = "video-file-1", title = "video file")
    .withDescription("video description")
    .withCaption("video caption")
    .withParseMode(ParseMode.Markdown)
    .withCaptionEntities(Seq(messageEntity1))
    .withReplyMarkup(inlineKeyboardMarkup1)
    .withInputMessageContent(inputMessageContent1)

  lazy val inlineQueryResult8: InlineQueryResult = InlineQueryResultCachedVoice
    .of(id = "30", voiceFileId = "voice-file-1", title = "voice file")
    .withCaption("voice caption")
    .withParseMode(ParseMode.Markdown)
    .withCaptionEntities(Seq(messageEntity1))
    .withReplyMarkup(inlineKeyboardMarkup1)
    .withInputMessageContent(inputMessageContent1)

  lazy val inlineQueryResult9: InlineQueryResult = InlineQueryResultArticle
    .of(id = "31", title = "article", inputMessageContent = inputMessageContent2)
    .withReplyMarkup(inlineKeyboardMarkup1)
    .withUrl("https://google.com/article")
    .withHideUrl(true)
    .withDescription("article description")
    .withThumbUrl("https://google.com/article_thumb.jpg")
    .withThumbWidth(300)
    .withThumbHeight(200)

  lazy val inlineQueryResult10: InlineQueryResult = InlineQueryResultAudio
    .of(id = "32", audioUrl = "https://google.com/audio.mp3", title = "audio file")
    .withCaption("audio caption")
    .withParseMode(ParseMode.Markdown)
    .withCaptionEntities(Seq(messageEntity1))
    .withPerformer("some performer")
    .withAudioDuration(50.seconds)
    .withReplyMarkup(inlineKeyboardMarkup1)
    .withInputMessageContent(inputMessageContent1)

  lazy val inlineQueryResult11: InlineQueryResult = InlineQueryResultContact
    .of(id = "33", phoneNumber = "+31680822212", firstName = "John")
    .withLastName("Wick")
    .withVcard("vcard")
    .withReplyMarkup(inlineKeyboardMarkup1)
    .withInputMessageContent(inputMessageContent1)
    .withThumbUrl("https://google.com/contact_thumb.jpeg")
    .withThumbWidth(250)
    .withThumbHeight(130)

  lazy val inlineQueryResult12: InlineQueryResult = InlineQueryResultGame
    .of(id = "34", gameShortName = "my game")
    .withReplyMarkup(inlineKeyboardMarkup1)

  lazy val inlineQueryResult13: InlineQueryResult = InlineQueryResultDocument
    .of(
      id = "35",
      title = "document title",
      documentUrl = "https://google.com/document.pdf",
      mimeType = "application/pdf"
    )
    .withCaption("document caption")
    .withParseMode(ParseMode.Markdown)
    .withCaptionEntities(Seq(messageEntity1))
    .withDescription("document description")
    .withReplyMarkup(inlineKeyboardMarkup1)
    .withInputMessageContent(inputMessageContent1)
    .withThumbUrl("https://google.com/document_thumb.jpeg")
    .withThumbWidth(210)
    .withThumbHeight(100)

  lazy val inlineQueryResult14: InlineQueryResult = InlineQueryResultGif
    .of(id = "36", gifUrl = "https://google.com/animation.gif", thumbUrl = "https://google.com/animation_thumb.jpeg")
    .withGifWidth(640)
    .withGifHeight(480)
    .withGifDuration(55.seconds)
    .withThumbMimeType("image/jpeg")
    .withTitle("gif title")
    .withCaption("gif title")
    .withParseMode(ParseMode.Markdown)
    .withCaptionEntities(Seq(messageEntity1))
    .withReplyMarkup(inlineKeyboardMarkup1)
    .withInputMessageContent(inputMessageContent1)

  lazy val inlineQueryResult15: InlineQueryResult = InlineQueryResultLocation
    .of(id = "37", longitude = 52.22, latitude = 4.55, title = "some location")
    .withHorizontalAccuracy(12.43)
    .withLivePeriod(10.minutes)
    .withHeading(21)
    .withProximityAlertRadius(11)
    .withReplyMarkup(inlineKeyboardMarkup1)
    .withInputMessageContent(inputMessageContent1)
    .withThumbUrl("https://google.com/location_thumb.jpeg")
    .withThumbWidth(210)
    .withThumbHeight(100)

  lazy val inlineQueryResult16: InlineQueryResult = InlineQueryResultMpeg4Gif
    .of(id = "38", mpeg4Url = "https://google.com/video.mpeg", thumbUrl = "https://google.com/video_thumb.jpeg")
    .withMpeg4Width(1024)
    .withMpeg4Height(768)
    .withMpeg4Duration(130.seconds)
    .withThumbMimeType("image/jpeg")
    .withTitle("video title")
    .withCaption("video caption")
    .withParseMode(ParseMode.Markdown)
    .withCaptionEntities(Seq(messageEntity1))
    .withReplyMarkup(inlineKeyboardMarkup1)
    .withInputMessageContent(inputMessageContent1)

  lazy val inlineQueryResult17: InlineQueryResult = InlineQueryResultPhoto
    .of(id = "39", photoUrl = "https://google.com/photo.jpeg", thumbUrl = "https://google.com/photo_thumb.jpeg")
    .withPhotoWidth(5000)
    .withPhotoHeight(2500)
    .withTitle("photo title")
    .withDescription("photo description")
    .withCaption("photo description")
    .withParseMode(ParseMode.Markdown)
    .withCaptionEntities(Seq(messageEntity1))
    .withReplyMarkup(inlineKeyboardMarkup1)
    .withInputMessageContent(inputMessageContent1)

  lazy val inlineQueryResult18: InlineQueryResult = InlineQueryResultVenue
    .of(
      id = "40",
      longitude = 52.22,
      latitude = 4.55,
      title = "NEMO Science Museum",
      address = "Oosterdok 2, 1011 VX Amsterdam"
    )
    .withFoursquareId("fsq1")
    .withFoursquareType("fsq-t-1")
    .withGooglePlaceId("gpi1")
    .withGooglePlaceType("gp-t-1")
    .withReplyMarkup(inlineKeyboardMarkup1)
    .withInputMessageContent(inputMessageContent1)
    .withThumbUrl("https://google.com/venue.jpeg")
    .withThumbWidth(200)
    .withThumbHeight(110)

  lazy val inlineQueryResult19: InlineQueryResult = InlineQueryResultVideo
    .of(
      id = "41",
      videoUrl = "https://google.com/video.mp4",
      mimeType = "video/mp4",
      thumbUrl = "https://google.com/video_thumb.jpeg",
      title = "video title"
    )
    .withCaption("video caption")
    .withParseMode(ParseMode.Markdown)
    .withCaptionEntities(Seq(messageEntity1))
    .withVideoWidth(1024)
    .withVideoHeight(768)
    .withVideoDuration(12.seconds)
    .withDescription("video description")
    .withReplyMarkup(inlineKeyboardMarkup1)
    .withInputMessageContent(inputMessageContent1)

  lazy val inlineQueryResult20: InlineQueryResult = InlineQueryResultVoice
    .of(id = "42", voiceUrl = "https://google.com/voice.mp3", title = "voice title")
    .withCaption("voice caption")
    .withParseMode(ParseMode.Markdown)
    .withCaptionEntities(Seq(messageEntity1))
    .withVoiceDuration(55.seconds)
    .withReplyMarkup(inlineKeyboardMarkup1)
    .withInputMessageContent(inputMessageContent1)

  lazy val inputMediaAnimation1: InputMedia = InputMediaAnimation
    .of(pathDescriptor("/tmp/one.gif"))
    .withThumb(urlDescriptor("https://google.com/animation_thumb.jpg"))
    .withCaption("my gif")
    .withParseMode(ParseMode.MarkdownV2)
    .withCaptionEntities(Seq(messageEntity1))
    .withWidth(1024)
    .withHeight(769)
    .withDuration(346)

  lazy val inputMediaAudio1: InputMedia = InputMediaAudio
    .of(pathDescriptor("/tmp/one.mp3"))
    .withThumb(pathDescriptor("/tmp/song_thumb.jpg"))
    .withCaption("my song")
    .withParseMode(ParseMode.HTML)
    .withCaptionEntities(Seq(messageEntity1))
    .withDuration(346)
    .withPerformer("Me")
    .withTitle("Best song ever")

  lazy val inputMediaDocument1: InputMedia = InputMediaDocument
    .of(pathDescriptor("/tmp/one.pdf"))
    .withThumb(pathDescriptor("/tmp/document_thumb.jpg"))
    .withCaption("my document")
    .withParseMode(ParseMode.Markdown)
    .withCaptionEntities(Seq(messageEntity1))
    .withDisableContentTypeDetection(true)

  lazy val inputMediaPhoto1: InputMedia = InputMediaPhoto
    .of(pathDescriptor("/tmp/one.jpg"))
    .withCaption("my photo")
    .withParseMode(ParseMode.Markdown)
    .withCaptionEntities(Seq(messageEntity1))

  lazy val inputMediaVideo1: InputMedia = InputMediaVideo
    .of(pathDescriptor("/tmp/one.mp4"))
    .withThumb(pathDescriptor("/tmp/video_thumb.jpg"))
    .withCaption("my video")
    .withParseMode(ParseMode.HTML)
    .withCaptionEntities(Seq(messageEntity1))
    .withWidth(1920)
    .withHeight(1080)
    .withDuration(980)
    .withSupportsStreaming(true)

  lazy val file1: File = File
    .of(fileId = "file-1", fileUniqueId = "unique-file-1")
    .withFileSize(20)
    .withFilePath("file-path")

  lazy val file2: File = File
    .of(fileId = "file-2", fileUniqueId = "unique-file-2")

  lazy val chatPermissions1: ChatPermissions = ChatPermissions.of
    .withCanSendMessages(true)
    .withCanSendMediaMessages(false)
    .withCanSendPolls(true)
    .withCanSendOtherMessages(false)
    .withCanAddWebPagePreviews(true)
    .withCanChangeInfo(false)
    .withCanInviteUsers(false)
    .withCanPinMessages(true)
    .withCanManageTopics(true)

  lazy val photoSize1: PhotoSize = PhotoSize
    .of(
      fileId = "photo-size-1",
      fileUniqueId = "unique-photo-size-1",
      width = 300,
      height = 200
    )
    .withFileSize(11)

  lazy val photoSize2: PhotoSize = PhotoSize
    .of(
      fileId = "photo-size-2",
      fileUniqueId = "unique-photo-size-2",
      width = 1300,
      height = 500
    )
    .withFileSize(421)

  lazy val animation1: Animation = Animation
    .of(
      fileId = "animation-file-1",
      fileUniqueId = "unique-animation-file-1",
      width = 640,
      height = 480,
      duration = 50.seconds
    )
    .withThumb(photoSize1)
    .withFileName("animation-file")
    .withMimeType("image/gif")
    .withFileSize(450)

  lazy val document1: Document = Document
    .of(
      fileId = "document-file-1",
      fileUniqueId = "unique-document-file-1"
    )
    .withThumb(photoSize2)
    .withFileName("document-1")
    .withMimeType("application/document")
    .withFileSize(5000)

  lazy val audio1: Audio = Audio
    .of(
      fileId = "audio-file-1",
      fileUniqueId = "unique-audio-file-1",
      duration = 422.seconds
    )
    .withPerformer("Amia Venera Landscape")
    .withTitle("A New Aurora")
    .withFileName("02 - A New Aurora.flac")
    .withMimeType("audio/x-flac,audio/flac")
    .withFileSize(300000000)
    .withThumb(photoSize1)

  lazy val botCommand1: BotCommand = BotCommand.of(command = "some command", description = "some description")

  lazy val botCommand2: BotCommand =
    BotCommand.of(command = "second command", description = "second command description")

  lazy val contact1: Contact = Contact
    .of(
      phoneNumber = "+31680822212",
      firstName = "John"
    )
    .withLastName("Wick")
    .withUserId(1)
    .withVcard("vcard")

  lazy val dice1: Dice = Dice
    .of(
      emoji = "🏀",
      value = 2
    )

  lazy val game1: Game = Game
    .of(
      title = "Guess who",
      description =
        "Guess Who? is a two-player board game where players each guess the identity of the other's chosen character",
      photo = Seq(photoSize1, photoSize2)
    )
    .withText("Some text")
    .withTextEntities(Seq(messageEntity1))
    .withAnimation(animation1)

  lazy val location1: Location = Location
    .of(
      longitude = 52.22,
      latitude = 4.55,
    )
    .withHorizontalAccuracy(1.23)
    .withLivePeriod(50)
    .withHeading(23)
    .withProximityAlertRadius(51)

  lazy val pollOption1: PollOption = PollOption.of(text = "Apple", voterCount = 10)
  lazy val pollOption2: PollOption = PollOption.of(text = "Peer", voterCount = 3)
  lazy val pollOption3: PollOption = PollOption.of(text = "Watermelon", voterCount = 8)

  lazy val poll1: Poll = Poll
    .of(
      id = "poll-1",
      question = "Favorite fruit",
      options = Seq(pollOption1, pollOption2, pollOption3),
      totalVoterCount = 21,
      isClosed = true,
      isAnonymous = false,
      `type` = PollType.Quiz,
      allowsMultipleAnswers = false
    )
    .withCorrectOptionId(0)
    .withExplanation("Which is your favorite fruit of all time?")
    .withExplanationEntities(Seq(messageEntity1))
    .withOpenPeriod(24.seconds)
    .withCloseDate(instant3)

  lazy val pollAnswer1: PollAnswer = PollAnswer
    .of(
      pollId = poll1.id,
      user = user2,
      optionIds = Seq(2, 3)
    )

  lazy val chatMember1: ChatMember = ChatMemberOwner
    .of(
      user = user1,
      isAnonymous = false
    )
    .withCustomTitle("title-1")

  lazy val chatMember2: ChatMember = ChatMemberLeft.of(user = user2)

  lazy val chatMember3: ChatMember = ChatMemberAdministrator
    .of(
      user = user1,
      canBeEdited = true,
      isAnonymous = false,
      canManageChat = true,
      canDeleteMessages = false,
      canManageVideoChats = true,
      canRestrictMembers = false,
      canPromoteMembers = false,
      canChangeInfo = true,
      canInviteUsers = false
    )
    .withCanPostMessages(true)
    .withCanEditMessages(false)
    .withCanPinMessages(true)
    .withCanManageTopics(false)
    .withCustomerTitle("custom title")

  lazy val chatMember4: ChatMember = ChatMemberBanned
    .of(
      user = user1,
      untilDate = 30000
    )

  lazy val chatMember5: ChatMember = ChatMemberMember.of(user = user1)

  lazy val chatMember6: ChatMember = ChatMemberRestricted
    .of(
      user = user1,
      isMember = true,
      canChangeInfo = false,
      canInviteUsers = true,
      canPinMembers = false,
      canManageTopics = true,
      canSendMessages = true,
      canSendMediaMessages = false,
      canSendPolls = false,
      canSendOtherMessages = true,
      canAddWebPagePreviews = true,
      untilDate = 50
    )

  lazy val botCommandScope1: BotCommandScope = BotCommandScopeDefault.of

  lazy val botCommandScope2: BotCommandScope = BotCommandScopeAllPrivateChats.of

  lazy val botCommandScope3: BotCommandScope = BotCommandScopeAllGroupChats.of

  lazy val botCommandScope4: BotCommandScope = BotCommandScopeAllChatAdministrators.of

  lazy val botCommandScope5: BotCommandScope = BotCommandScopeChat.of(chatId = "512")

  lazy val botCommandScope6: BotCommandScope = BotCommandScopeChatAdministrators.of(chatId = "513")

  lazy val botCommandScope7: BotCommandScope = BotCommandScopeChatMember.of(chatId = "513", userId = 221)

  lazy val menuButton1: MenuButton = MenuButtonCommands.of

  lazy val menuButton2: MenuButton = MenuButtonWebApp.of(text = "button text", webApp = webAppInfo1)

  lazy val menuButton3: MenuButton = MenuButtonDefault.of

  lazy val chatInviteLink1: ChatInviteLink = ChatInviteLink
    .of(
      inviteLink = "http://google.com/invite",
      creator = user1,
      createsJoinRequest = true,
      isPrimary = true,
      isRevoked = false
    )
    .withName("invite-1")
    .withExpirationDate(instant4)
    .withMemberLimit(22)
    .withPendingJoinRequestCount(6)

  lazy val chatAdministratorRights1: ChatAdministratorRights = ChatAdministratorRights
    .of(
      isAnonymous = true,
      canManageChat = false,
      canDeleteMessages = true,
      canManageVideoChats = false,
      canRestrictMembers = true,
      canPromoteMembers = false,
      canChangeInfo = true,
      canInviteUsers = false
    )
    .withCanPostMessages(true)
    .withCanEditMessages(false)
    .withCanPinMessages(true)
    .withCanManageTopics(false)

  lazy val chatMemberUpdated1: ChatMemberUpdated = ChatMemberUpdated
    .of(
      chat = chat1,
      from = user1,
      date = instant2,
      oldChatMember = chatMember1,
      newChatMember = chatMember2
    )
    .withInviteLink(chatInviteLink1)

  lazy val chatJoinRequest1: ChatJoinRequest = ChatJoinRequest
    .of(
      chat = chat1,
      from = user2,
      date = instant4
    )
    .withBio("bio-1")
    .withInviteLink(chatInviteLink1)

  lazy val maskPosition1: MaskPosition = MaskPosition
    .of(
      point = MaskPointType.Forehead,
      xShift = 10.2,
      yShift = 5.4,
      scale = 1.3
    )

  lazy val sticker1: Sticker = Sticker
    .of(
      fileId = "sticker-1",
      fileUniqueId = "unique-sticker-1",
      `type` = StickerType.CustomEmoji,
      width = 80,
      height = 50,
      isAnimated = true,
      isVideo = false
    )
    .withThumb(photoSize1)
    .withEmoji("🎰")
    .withSetName("set-1")
    .withPremiumAnimation(file1)
    .withMaskPosition(maskPosition1)
    .withCustomEmojiId("custom-emoji-id-1")
    .withFileSize(500)

  lazy val venue1: Venue = Venue
    .of(
      location = location1,
      title = "NEMO Science Museum",
      address = "Oosterdok 2, 1011 VX Amsterdam"
    )
    .withFoursquareId("fsq1")
    .withFoursquareType("fsq-t-1")
    .withGooglePlaceId("gpi1")
    .withGooglePlaceType("gp-t-1")

  lazy val video1: Video = Video
    .of(
      fileId = "video-1",
      fileUniqueId = "unique-video-1",
      width = 640,
      height = 480,
      duration = 15.seconds
    )
    .withThumb(photoSize1)
    .withFileName("video-1")
    .withMimeType("video/mp4")
    .withFileSize(54000)

  lazy val videoNote1: VideoNote = VideoNote
    .of(
      fileId = "video-note-1",
      fileUniqueId = "unique-video-note-1",
      length = 4000,
      duration = 23.seconds
    )
    .withThumb(photoSize2)
    .withFileSize(64441)

  lazy val voice1: Voice = Voice
    .of(
      fileId = "voice-1",
      fileUniqueId = "unique-voice-1",
      duration = 50.seconds
    )
    .withMimeType("audio/mpeg")
    .withFileSize(1355)

  lazy val invoice1: Invoice = Invoice
    .of(
      title = "Shopping",
      description = "Your invoice",
      startParameter = "param-1",
      currency = "EUR",
      totalAmount = 53
    )

  lazy val labeledPrice1: LabeledPrice = LabeledPrice.of(label = "some label", amount = 225)

  lazy val shippingAddress1: ShippingAddress = ShippingAddress
    .of(
      countryCode = "NL",
      state = "Utrecht",
      city = "Amersfoort",
      streetLine1 = "Some Street 1",
      streetLine2 = "Other",
      postCode = "3821KL"
    )

  lazy val shippingOption1: ShippingOption = ShippingOption
    .of(id = "shipping-option-1", title = "shipping option title", prices = Seq(labeledPrice1))

  lazy val orderInfo1: OrderInfo = OrderInfo.of
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

  lazy val encryptedCredentials1: EncryptedCredentials = EncryptedCredentials
    .of(
      data = "data-1",
      hash = "hash-1",
      secret = "secret-1"
    )

  lazy val passportFile1: PassportFile = PassportFile
    .of(
      fileId = "file-1",
      fileUniqueId = "unique-file-1",
      fileSize = 200,
      fileDate = instant1
    )

  lazy val passportFile2: PassportFile = PassportFile
    .of(
      fileId = "file-2",
      fileUniqueId = "unique-file-2",
      fileSize = 150,
      fileDate = instant2
    )

  lazy val passportFile3: PassportFile = PassportFile
    .of(
      fileId = "file-3",
      fileUniqueId = "unique-file-3",
      fileSize = 125,
      fileDate = instant3
    )

  lazy val passportFile4: PassportFile = PassportFile
    .of(
      fileId = "file-4",
      fileUniqueId = "unique-file-4",
      fileSize = 63,
      fileDate = instant4
    )

  lazy val passportFile5: PassportFile = PassportFile
    .of(
      fileId = "file-5",
      fileUniqueId = "unique-file-5",
      fileSize = 11,
      fileDate = instant2
    )

  lazy val encryptedPassportElement1: EncryptedPassportElement = EncryptedPassportElement
    .of(
      `type` = PassportElementType.Address,
      hash = "hash-1"
    )
    .withData("address-1")
    .withPhoneNumber("+31630911234")
    .withEmail("email@gmail.com")
    .withFiles(Seq(passportFile1))
    .withFrontSide(passportFile2)
    .withReverseSide(passportFile3)
    .withSelfie(passportFile4)
    .withTranslation(Seq(passportFile5))

  lazy val passportData1: PassportData = PassportData
    .of(
      data = Seq(encryptedPassportElement1),
      credentials = encryptedCredentials1
    )

  lazy val proximityAlertTriggered1: ProximityAlertTriggered = ProximityAlertTriggered
    .of(
      traveler = user1,
      watcher = user2,
      distance = 20
    )

  lazy val videoChatScheduled1: VideoChatScheduled = VideoChatScheduled.of(startDate = instant4)

  lazy val videoChatStarted1: VideoChatStarted = VideoChatStarted.of

  lazy val videoChatEnded1: VideoChatEnded = VideoChatEnded.of(duration = 10.minutes)

  lazy val videoChatParticipantsInvited1: VideoChatParticipantsInvited = VideoChatParticipantsInvited.of(
    users = Seq(user2, user1)
  )

  lazy val webAppData1: WebAppData = WebAppData.of(
    data = "data-1",
    buttonText = "button one"
  )

  lazy val forumTopicCreated1: ForumTopicCreated = ForumTopicCreated
    .of(name = "topic-1", iconColor = 255)
    .withIconCustomEmojiId("emoji-1")

  lazy val forumTopicClosed1: ForumTopicClosed = ForumTopicClosed.of

  lazy val forumTopicReopened1: ForumTopicReopened = ForumTopicReopened.of

  lazy val inlineQuery1: InlineQuery = InlineQuery
    .of(
      id = "query-1",
      from = user1,
      query = "test",
      offset = "1"
    )
    .withChatType(ChatType.Sender)
    .withLocation(location1)

  lazy val chatPhoto1: ChatPhoto = ChatPhoto
    .of(
      smallFileId = "small-file-id-1",
      smallFileUniqueId = "unique-small-file-id-1",
      bigFileId = "big-file-id-1",
      bigFileUniqueId = "unique-big-file-id-1"
    )

  lazy val chatLocation1: ChatLocation = ChatLocation
    .of(
      location = location1,
      address = "Jachthaven 1, 2172 JX Sassenheim"
    )

  lazy val chosenInlineResult1: domain.ChosenInlineResult = domain.ChosenInlineResult
    .of(
      resultId = "result-1",
      from = user1,
      query = "query-1"
    )
    .withLocation(location1)
    .withInlineMessageId("message-1")

  lazy val callbackQuery1: CallbackQuery = CallbackQuery
    .of(
      id = "callback-1",
      from = user1,
      chatInstance = "chat-1"
    )
    .withMessage(message1)
    .withInlineMessageId("inline-message-1")
    .withData("data-1")
    .withGameShortName("game-short-name")

  lazy val shippingQuery1: ShippingQuery = ShippingQuery
    .of(
      id = "shipping-query-1",
      from = user1,
      invoicePayload = "payload-1",
      shippingAddress = shippingAddress1
    )

  lazy val preCheckoutQuery1: PreCheckoutQuery = PreCheckoutQuery
    .of(
      id = "pre-checkout-query-1",
      from = user1,
      currency = "USD",
      totalAmount = 20,
      invoicePayload = "payload-1"
    )
    .withShippingOptionId("option-id-1")
    .withOrderInfo(orderInfo1)

  lazy val webhookInfo1: WebhookInfo = WebhookInfo
    .of(url = "https://google.com/webhook", hasCustomCertificate = true, pendingUpdateCount = 6)
    .withIpAddress("192.168.2.1")
    .withLastErrorDate(instant1)
    .withLastErrorMessage("something went wrong")
    .withLastSynchronizationErrorDate(instant3)
    .withMaxConnections(25)
    .withAllowedUpdates(Set(UpdateType.Message, UpdateType.Poll))

  lazy val forumTopic1: ForumTopic = ForumTopic
    .of(
      messageThreadId = 21,
      name = "General",
      iconColor = 9367192
    )
    .withIconCustomEmojiId("emoji1")

  lazy val messageAutoDeleteTimerChanged1: MessageAutoDeleteTimerChanged =
    MessageAutoDeleteTimerChanged(messageAutoDeleteTime = 30.seconds)

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

  lazy val fullUpdate1: Update = Update
    .of(83)
    .withMessage(message1)
    .withEditedMessage(message2)
    .withChannelPost(message3)
    .withEditedChannelPost(message4)
    .withInlineQuery(inlineQuery1)
    .withChosenInlineResult(chosenInlineResult1)
    .withCallbackQuery(callbackQuery1)
    .withShippingQuery(shippingQuery1)
    .withPreCheckoutQuery(preCheckoutQuery1)
    .withPoll(poll1)
    .withPollAnswer(pollAnswer1)
    .withMyChatMember(chatMemberUpdated1)
    .withChatMember(chatMemberUpdated1)
    .withChatJoinRequest(chatJoinRequest1)

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

  lazy val failureApiResponse1: ApiResponse[String] = FailureApiResponse(
    ok = false,
    errorCode = 500,
    description = "server error"
  )

  lazy val successApiResponse1: ApiResponse[String] = SuccessApiResponse(result = "hello world")
}
