package io.github.aalbul.zio.telegram.bot

import io.github.aalbul.zio.telegram.command.*
import io.github.aalbul.zio.telegram.domain.*
import io.github.aalbul.zio.telegram.domain.ChatActions.ChatAction
import io.github.aalbul.zio.telegram.engine.BotEngine
import zio.stream.ZStream

trait Bot {

  /** A simple method for testing your bot's authentication token. Requires no parameters. Returns basic information
    * about the bot in form of a [[User]] object.
    * @return
    *   [[GetMe]] builder
    */
  def getMe: GetMe

  /** Use this method to log out from the cloud Bot API server before launching the bot locally. You must log out the
    * bot before running it locally, otherwise there is no guarantee that the bot will receive updates. After a
    * successful call, you can immediately log in on a local server, but will not be able to log in back to the cloud
    * Bot API server for 10 minutes. Returns ''True'' on success. Requires no parameters.
    * @return
    *   [[LogOut]] builder
    */
  def logOut: LogOut

  /** Use this method to close the bot instance before moving it from one local server to another. You need to delete
    * the webhook before calling this method to ensure that the bot isn't launched again after server restart. The
    * method will return error ''429'' in the first 10 minutes after the bot is launched. Returns ''True'' on success.
    * Requires no parameters.
    * @return
    *   [[Close]] builder
    */
  def close: Close

  /** Create infinite stream of updates
    * @param chunkSize
    *   Limits the number of updates to be retrieved per chunk. Values between 1-100 are accepted. Defaults to 100.
    * @return
    *   [[ZStream]] of [[Update]]'s
    */
  def streamUpdates(chunkSize: Long = 100L): ZStream[BotEngine, Throwable, Update]

  /** Use this method to send text messages. On success, the sent [[Message]] is returned.
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param text
    *   Text of the message to be sent, '''1-4096''' characters after entities parsing
    * @return
    *   [[SendMessage]] builder
    */
  def sendMessage(chatId: String, text: String): SendMessage

  /** Use this method to forward messages of any kind. Service messages can't be forwarded. On success, the sent
    * [[Message]] is returned.
    * @param messageId
    *   Message identifier in the chat specified in `fromChatId`
    * @param fromChatId
    *   Unique identifier for the chat where the original message was sent (or channel username in the format
    *   \@channelusername)
    * @param toChatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @return
    *   [[ForwardMessage]] builder
    */
  def forwardMessage(messageId: Long, fromChatId: String, toChatId: String): ForwardMessage
  def copyMessage(chatId: String, fromChatId: String, messageId: String): CopyMessage
  def sendPhoto(chatId: String, photo: FileDescriptor): SendPhoto
  def sendAudio(chatId: String, audio: FileDescriptor): SendAudio
  def sendDocument(chatId: String, document: FileDescriptor): SendDocument
  def sendVideo(chatId: String, video: FileDescriptor): SendVideo
  def sendAnimation(chatId: String, animation: FileDescriptor): SendAnimation
  def sendVoice(chatId: String, voice: FileDescriptor): SendVoice
  def sendVideoNote(chatId: String, voiceNote: FileDescriptor): SendVideoNote
  def sendMediaGroup(chatId: String, media: Seq[InputMedia]): SendMediaGroup
  def sendLocation(chatId: String, latitude: Double, longitude: Double): SendLocation
  def editMessageLiveLocation(latitude: Double, longitude: Double): EditMessageLiveLocation
  def stopMessageLiveLocation: StopMessageLiveLocation
  def sendVenue(chatId: String, latitude: Double, longitude: Double, title: String, address: String): SendVenue
  def sendContact(chatId: String, phoneNumber: String, firstName: String): SendContact
  def sendPoll(chatId: String, question: String, options: Seq[String]): SendPoll
  def sendDice(chatId: String): SendDice
  def sendChatAction(chatId: String, action: ChatAction): SendChatAction
  def getUserProfilePhotos(userId: String): GetUserProfilePhotos
  def getFile(fileId: String): GetFile
  def banChatMember(chatId: String, userId: String): BanChatMember

  /** Use this method to unban a previously banned user in a supergroup or channel. The user will not return to the
    * group or channel automatically, but will be able to join via link, etc. The bot must be an administrator for this
    * to work. By default, this method guarantees that after the call the user is not a member of the chat, but will be
    * able to join it. So if the user is a member of the chat they will also be removed from the chat. If you don't want
    * this, use the parameter ''only_if_banned''. Returns ''True'' on success.
    *
    * @param chatId
    *   Unique identifier for the target group or username of the target supergroup or channel (in the format
    *   \@channelusername)
    * @param userId
    *   Unique identifier of the target user
    * @return
    *   [[UnbanChatMember]] builder
    */
  def unbanChatMember(chatId: String, userId: String): UnbanChatMember

  /** Use this method to set default chat permissions for all members. The bot must be an administrator in the group or
    * a supergroup for this to work and must have the ''can_restrict_members'' administrator rights. Returns ''True'' on
    * success.
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @param permissions
    *   new default [[ChatPermissions]]
    * @return
    *   [[SetChatPermissions]] builder
    */
  def setChatPermissions(chatId: String, permissions: ChatPermissions): SetChatPermissions
}
