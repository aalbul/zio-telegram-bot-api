package io.github.aalbul.zio.telegram.bot

import io.github.aalbul.zio.telegram.command.*
import io.github.aalbul.zio.telegram.domain.*
import io.github.aalbul.zio.telegram.engine.BotEngine
import io.github.aalbul.zio.telegram.projection.ProjectionBuilder
import zio.stream.ZStream
import zio.{ULayer, ZLayer}

object DefaultBot {
  val layer: ULayer[Bot] = ZLayer.succeed(new DefaultBot)
}

class DefaultBot extends Bot {
  override def getUpdates: GetUpdates = GetUpdates.of
  override def setWebhook(url: String): SetWebhook = SetWebhook.of(url)
  override def deleteWebhook: DeleteWebhook = DeleteWebhook.of
  override def getWebhookInfo: GetWebhookInfo = GetWebhookInfo.of
  override def getMe: GetMe = GetMe.of
  override def logOut: LogOut = LogOut.of
  override def close: Close = Close.of
  override def streamUpdates(
    chunkSize: Long = 100L,
    allowedTypes: Set[UpdateType]
  ): ZStream[BotEngine, Throwable, Update] = StreamUpdates(chunkSize, allowedTypes)
  override def project: ProjectionBuilder = ProjectionBuilder.empty
  override def sendMessage(chatId: String, text: String): SendMessage = SendMessage.of(chatId, text)
  override def forwardMessage(messageId: Long, fromChatId: String, toChatId: String): ForwardMessage =
    ForwardMessage.of(messageId, fromChatId, toChatId)
  override def copyMessage(chatId: String, fromChatId: String, messageId: String): CopyMessage =
    CopyMessage.of(chatId, fromChatId, messageId)
  override def sendPhoto(chatId: String, photo: FileDescriptor): SendPhoto = SendPhoto.of(chatId, photo)
  override def sendAudio(chatId: String, audio: FileDescriptor): SendAudio = SendAudio.of(chatId, audio)
  override def sendDocument(chatId: String, document: FileDescriptor): SendDocument = SendDocument.of(chatId, document)
  override def sendVideo(chatId: String, video: FileDescriptor): SendVideo = SendVideo.of(chatId, video)
  override def sendAnimation(chatId: String, animation: FileDescriptor): SendAnimation =
    SendAnimation.of(chatId, animation)
  override def sendVoice(chatId: String, voice: FileDescriptor): SendVoice = SendVoice.of(chatId, voice)
  override def sendVideoNote(chatId: String, voiceNote: FileDescriptor): SendVideoNote =
    SendVideoNote.of(chatId, voiceNote)
  override def sendMediaGroup(chatId: String, media: Seq[InputMedia]): SendMediaGroup = SendMediaGroup.of(chatId, media)
  override def sendLocation(chatId: String, latitude: Double, longitude: Double): SendLocation =
    SendLocation.of(chatId, latitude, longitude)
  override def editMessageLiveLocation(latitude: Double, longitude: Double): EditMessageLiveLocation =
    EditMessageLiveLocation.of(latitude, longitude)
  override def stopMessageLiveLocation: StopMessageLiveLocation = StopMessageLiveLocation.of
  override def sendVenue(
    chatId: String,
    latitude: Double,
    longitude: Double,
    title: String,
    address: String
  ): SendVenue = SendVenue.of(chatId, latitude, longitude, title, address)
  override def sendContact(chatId: String, phoneNumber: String, firstName: String): SendContact =
    SendContact.of(chatId, phoneNumber, firstName)
  override def sendPoll(chatId: String, question: String, options: Seq[String]): SendPoll =
    SendPoll.of(chatId, question, options)
  override def sendDice(chatId: String): SendDice = SendDice.of(chatId)
  override def sendChatAction(chatId: String, action: ChatAction): SendChatAction = SendChatAction.of(chatId, action)
  override def getUserProfilePhotos(userId: Long): GetUserProfilePhotos = GetUserProfilePhotos.of(userId)
  override def getFile(fileId: String): GetFile = GetFile.of(fileId)
  override def banChatMember(chatId: String, userId: Long): BanChatMember = BanChatMember.of(chatId, userId)
  override def unbanChatMember(chatId: String, userId: Long): UnbanChatMember = UnbanChatMember.of(chatId, userId)
  override def restrictChatMember(chatId: String, userId: Long, permissions: ChatPermissions): RestrictChatMember =
    RestrictChatMember.of(chatId, userId, permissions)
  override def promoteChatMember(chatId: String, userId: Long): PromoteChatMember =
    PromoteChatMember.of(chatId, userId)
  override def setChatAdministratorCustomTitle(
    chatId: String,
    userId: Long,
    customTitle: String
  ): SetChatAdministratorCustomTitle =
    SetChatAdministratorCustomTitle.of(chatId, userId, customTitle)
  override def banChatSenderChat(chatId: String, senderChatId: Long): BanChatSenderChat =
    BanChatSenderChat.of(chatId, senderChatId)
  override def unbanChatSenderChat(chatId: String, senderChatId: Long): UnbanChatSenderChat =
    UnbanChatSenderChat.of(chatId, senderChatId)
  override def setChatPermissions(chatId: String, permissions: ChatPermissions): SetChatPermissions =
    SetChatPermissions.of(chatId, permissions)
  override def exportChatInviteLink(chatId: String): ExportChatInviteLink = ExportChatInviteLink.of(chatId)
  override def createChatInviteLink(chatId: String): CreateChatInviteLink = CreateChatInviteLink.of(chatId)
  override def editChatInviteLink(chatId: String, inviteLink: String): EditChatInviteLink =
    EditChatInviteLink.of(chatId, inviteLink)
  override def revokeChatInviteLink(chatId: String, inviteLink: String): RevokeChatInviteLink =
    RevokeChatInviteLink.of(chatId, inviteLink)
  override def approveChatJoinRequest(chatId: String, userId: Long): ApproveChatJoinRequest =
    ApproveChatJoinRequest.of(chatId, userId)
  override def declineChatJoinRequest(chatId: String, userId: Long): DeclineChatJoinRequest =
    DeclineChatJoinRequest.of(chatId, userId)
  override def setChatPhoto(chatId: String, photo: FileDescriptor): SetChatPhoto = SetChatPhoto.of(chatId, photo)
  override def deleteChatPhoto(chatId: String): DeleteChatPhoto = DeleteChatPhoto.of(chatId)
  override def setChatTitle(chatId: String, title: String): SetChatTitle = SetChatTitle.of(chatId, title)
  override def setChatDescription(chatId: String): SetChatDescription = SetChatDescription.of(chatId)
  override def pinChatMessage(chatId: String, messageId: Long): PinChatMessage = PinChatMessage.of(chatId, messageId)
  override def unpinChatMessage(chatId: String): UnpinChatMessage = UnpinChatMessage.of(chatId)
  override def unpinAllChatMessages(chatId: String): UnpinAllChatMessages = UnpinAllChatMessages.of(chatId)
  override def leaveChat(chatId: String): LeaveChat = LeaveChat.of(chatId)
  override def getChat(chatId: String): GetChat = GetChat.of(chatId)
  override def getChatAdministrators(chatId: String): GetChatAdministrators = GetChatAdministrators.of(chatId)
  override def getChatMemberCount(chatId: String): GetChatMemberCount = GetChatMemberCount.of(chatId)
  override def getChatMember(chatId: String, userId: Long): GetChatMember = GetChatMember.of(chatId, userId)
  override def setChatStickerSet(chatId: String, stickerSetName: String): SetChatStickerSet =
    SetChatStickerSet.of(chatId, stickerSetName)
  override def deleteChatStickerSet(chatId: String): DeleteChatStickerSet = DeleteChatStickerSet.of(chatId)
  override def getForumTopicIconStickers: GetForumTopicIconStickers = GetForumTopicIconStickers.of
  override def createForumTopic(chatId: String, name: String): CreateForumTopic = CreateForumTopic.of(chatId, name)
  override def editForumTopic(
    chatId: String,
    messageThreadId: Long,
    name: String,
    iconCustomEmojiId: String
  ): EditForumTopic = EditForumTopic.of(chatId, messageThreadId, name, iconCustomEmojiId)
  override def closeForumTopic(chatId: String, messageThreadId: Long): CloseForumTopic =
    CloseForumTopic.of(chatId, messageThreadId)
  override def reopenForumTopic(chatId: String, messageThreadId: Long): ReopenForumTopic =
    ReopenForumTopic.of(chatId, messageThreadId)
  override def deleteForumTopic(chatId: String, messageThreadId: Long): DeleteForumTopic =
    DeleteForumTopic.of(chatId, messageThreadId)
  override def unpinAllForumTopicMessages(chatId: String, messageThreadId: Long): UnpinAllForumTopicMessages =
    UnpinAllForumTopicMessages.of(chatId, messageThreadId)
  override def answerCallbackQuery(callbackQueryId: String): AnswerCallbackQuery =
    AnswerCallbackQuery.of(callbackQueryId)
  override def setMyCommands(commands: Seq[BotCommand]): SetMyCommands = SetMyCommands.of(commands)
  override def deleteMyCommands: DeleteMyCommands = DeleteMyCommands.of
  override def getMyCommands: GetMyCommands = GetMyCommands.of
  override def setChatMenuButton: SetChatMenuButton = SetChatMenuButton.of
  override def getChatMenuButton: GetChatMenuButton = GetChatMenuButton.of
  override def setMyDefaultAdministratorRights: SetMyDefaultAdministratorRights = SetMyDefaultAdministratorRights.of
  override def getMyDefaultAdministratorRights: GetMyDefaultAdministratorRights = GetMyDefaultAdministratorRights.of
  override def editMessageText(text: String): EditMessageText = EditMessageText.of(text)
  override def editMessageCaption: EditMessageCaption = EditMessageCaption.of
  override def editMessageMedia(media: InputMedia): EditMessageMedia = EditMessageMedia.of(media)
  override def editMessageReplyMarkup: EditMessageReplyMarkup = EditMessageReplyMarkup.of
  override def stopPoll(chatId: String, messageId: Long): StopPoll = StopPoll.of(chatId, messageId)
  override def deleteMessage(chatId: String, messageId: Long): DeleteMessage = DeleteMessage.of(chatId, messageId)
  override def sendSticker(chatId: String, sticker: FileDescriptor): SendSticker = SendSticker.of(chatId, sticker)
  override def getStickerSet(name: String): GetStickerSet = GetStickerSet.of(name)
  override def getCustomEmojiStickers(customEmojiIds: Seq[String]): GetCustomEmojiStickers =
    GetCustomEmojiStickers.of(customEmojiIds)
  override def uploadStickerFile(userId: Long, pngSticker: FileDescriptor): UploadStickerFile =
    UploadStickerFile.of(userId, pngSticker)
  override def createNewStickerSet(userId: Long, name: String, title: String, emojis: String): CreateNewStickerSet =
    CreateNewStickerSet.of(userId, name, title, emojis)
  override def addStickerToSet(userId: Long, name: String, emojis: String): AddStickerToSet =
    AddStickerToSet.of(userId, name, emojis)
  override def answerInlineQuery(inlineQueryId: String, results: Seq[InlineQueryResult]): AnswerInlineQuery =
    AnswerInlineQuery.of(inlineQueryId, results)
  override def answerWebAppQuery(webAppQueryId: String, result: InlineQueryResult): AnswerWebAppQuery =
    AnswerWebAppQuery.of(webAppQueryId, result)
  override def sendInvoice(
    chatId: String,
    title: String,
    description: String,
    payload: String,
    providerToken: String,
    currency: String,
    prices: Seq[LabeledPrice]
  ): SendInvoice = SendInvoice.of(chatId, title, description, payload, providerToken, currency, prices)
  override def createInvoiceLink(
    title: String,
    description: String,
    payload: String,
    providerToken: String,
    currency: String,
    prices: Seq[LabeledPrice]
  ): CreateInvoiceLink = CreateInvoiceLink.of(title, description, payload, providerToken, currency, prices)
  override def answerShippingQuery(shippingQueryId: String, ok: Boolean): AnswerShippingQuery =
    AnswerShippingQuery.of(shippingQueryId, ok)
  override def answerPreCheckoutQuery(preCheckoutQueryId: String, ok: Boolean): AnswerPreCheckoutQuery =
    AnswerPreCheckoutQuery.of(preCheckoutQueryId, ok)
  override def setPassportDataErrors(userId: Long, errors: Seq[PassportElementError]): SetPassportDataErrors =
    SetPassportDataErrors.of(userId, errors)
  override def sendGame(chatId: Long, gameShortName: String): SendGame = SendGame.of(chatId, gameShortName)
  override def setGameScore(userId: Long, score: Long): SetGameScore = SetGameScore.of(userId, score)
  override def getGameHighScores(userId: Long): GetGameHighScores = GetGameHighScores.of(userId)
}
