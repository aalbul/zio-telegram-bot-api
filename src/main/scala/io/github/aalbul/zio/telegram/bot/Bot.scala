package io.github.aalbul.zio.telegram.bot

import io.github.aalbul.zio.telegram.command.*
import io.github.aalbul.zio.telegram.domain.*
import io.github.aalbul.zio.telegram.engine.BotEngine
import io.github.aalbul.zio.telegram.projection.ProjectionBuilder
import zio.stream.ZStream

trait Bot {

  /** Use this method to receive incoming updates using long polling
    * ([[https://en.wikipedia.org/wiki/Push_technology#Long_polling wiki]]). Returns an Array of
    * [[https://core.telegram.org/bots/api#update Update]] objects.
    *
    * Notes:
    *   - \1. This method will not work if an outgoing webhook is set up.
    *   - 2. In order to avoid getting duplicate updates, recalculate offset after each server response.
    *
    * @return
    *   [[GetUpdates]] builder
    */
  def getUpdates: GetUpdates

  /** Use this method to specify a URL and receive incoming updates via an outgoing webhook. Whenever there is an update
    * for the bot, we will send an HTTPS POST request to the specified URL, containing a JSON-serialized
    * [[https://core.telegram.org/bots/api#update Update]]. In case of an unsuccessful request, we will give up after a
    * reasonable amount of attempts. Returns True on success.
    *
    * If you'd like to make sure that the webhook was set by you, you can specify secret data in the parameter
    * secret_token. If specified, the request will contain a header “X-Telegram-Bot-Api-Secret-Token” with the secret
    * token as content.
    *
    * Notes
    *
    *   - \1. You will not be able to receive updates using [[https://core.telegram.org/bots/api#getupdates getUpdates]]
    *     for as long as an outgoing webhook is set up.
    *   - 2. To use a self-signed certificate, you need to upload your
    *     [[https://core.telegram.org/bots/self-signed public key certificate]] using certificate parameter. Please
    *     upload as InputFile, sending a String will not work.
    *   - 3. Ports currently supported for webhooks: 443, 80, 88, 8443.
    *
    * If you're having any trouble setting up webhooks, please check out this
    * [[https://core.telegram.org/bots/webhooks amazing guide to webhooks]].
    *
    * @param url
    *   HTTPS URL to send updates to. Use an empty string to remove webhook integration
    * @return
    *   [[SetWebhook]] builder
    */
  def setWebhook(url: String): SetWebhook

  /** Use this method to remove webhook integration if you decide to switch back to
    * [[https://core.telegram.org/bots/api#getupdates getUpdates]]. Returns True on success.
    *
    * @return
    *   [[DeleteWebhook]] builder
    */
  def deleteWebhook: DeleteWebhook

  /** Use this method to get current webhook status. Requires no parameters. On success, returns a
    * [[https://core.telegram.org/bots/api#webhookinfo WebhookInfo]] object. If the bot is using
    * [[https://core.telegram.org/bots/api#getupdates getUpdates]], will return an object with the url field empty.
    *
    * @return
    *   [[GetWebhookInfo]] builder
    */
  def getWebhookInfo: GetWebhookInfo

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
    * @param allowedTypes
    *   A list of the update types you want your bot to receive. For example, specify [“message”, “edited_channel_post”,
    *   “callback_query”] to only receive updates of these types. See Update for a complete list of available update
    *   types. Specify an empty list to receive all update types except chat_member (default). If not specified, the
    *   previous setting will be used.
    *
    * Please note that this parameter doesn't affect updates created before the call to the getUpdates, so unwanted
    * updates may be received for a short period of time.
    *
    * @return
    *   [[ZStream]] of [[Update]]'s
    */
  def streamUpdates(
    chunkSize: Long = 100L,
    allowedTypes: Set[UpdateType] = Set.empty
  ): ZStream[BotEngine, Throwable, Update]

  /** Create stream projection builder
    * @return
    *   [[ProjectionBuilder]] instance
    */
  def project: ProjectionBuilder

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

  /** Use this method to copy messages of any kind. Service messages and invoice messages can't be copied. The method is
    * analogous to the method [[ForwardMessage]], but the copied message doesn't have a link to the original message.
    * Returns the `MessageId` of the sent message on success.
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param fromChatId
    *   Unique identifier for the chat where the original message was sent (or channel username in the format
    *   \@channelusername)
    * @param messageId
    *   Message identifier in the chat specified in ''from_chat_id''
    * @return
    *   [[CopyMessage]] builder
    */
  def copyMessage(chatId: String, fromChatId: String, messageId: String): CopyMessage

  /** Use this method to send photos. On success, the sent [[Message]] is returned.
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param photo
    *   Photo to send. Pass a file_id as String to send a photo that exists on the Telegram servers (recommended), pass
    *   an HTTP URL as a String for Telegram to get a photo from the Internet, or upload a new photo using
    *   multipart/form-data. The photo must be at most 10 MB in size. The photo's width and height must not exceed 10000
    *   in total. Width and height ratio must be at most 20.
    *   [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]
    * @return
    *   [[SendPhoto]] builder
    */
  def sendPhoto(chatId: String, photo: FileDescriptor): SendPhoto

  /** Use this method to send audio files, if you want Telegram clients to display them in the music player. Your audio
    * must be in the .MP3 or .M4A format. On success, the sent [[Message]] is returned. Bots can currently send audio
    * files of up to 50 MB in size, this limit may be changed in the future.
    *
    * For sending voice messages, use the [[SendVoice]] method instead.
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param audio
    *   Audio file to send. Pass a file_id as String to send an audio file that exists on the Telegram servers
    *   (recommended), pass an HTTP URL as a String for Telegram to get an audio file from the Internet, or upload a new
    *   one using multipart/form-data.
    *   [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]
    * @return
    *   [[SendAudio]] builder
    */
  def sendAudio(chatId: String, audio: FileDescriptor): SendAudio

  /** Use this method to send general files. On success, the sent [[Message]] is returned. Bots can currently send files
    * of any type of up to 50 MB in size, this limit may be changed in the future.
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param document
    *   File to send. Pass a file_id as String to send a file that exists on the Telegram servers (recommended), pass an
    *   HTTP URL as a String for Telegram to get a file from the Internet, or upload a new one using
    *   multipart/form-data. [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]
    * @return
    *   [[SendDocument]] builder
    */
  def sendDocument(chatId: String, document: FileDescriptor): SendDocument

  /** Use this method to send video files, Telegram clients support MPEG4 videos (other formats may be sent as
    * [[Document]]). On success, the sent [[Message]] is returned. Bots can currently send video files of up to 50 MB in
    * size, this limit may be changed in the future.
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param video
    *   Video to send. Pass a file_id as String to send a video that exists on the Telegram servers (recommended), pass
    *   an HTTP URL as a String for Telegram to get a video from the Internet, or upload a new video using
    *   multipart/form-data. [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]
    * @return
    *   [[SendVideo]] builder
    */
  def sendVideo(chatId: String, video: FileDescriptor): SendVideo

  /** Use this method to send animation files (GIF or H.264/MPEG-4 AVC video without sound). On success, the sent
    * [[Message]] is returned. Bots can currently send animation files of up to 50 MB in size, this limit may be changed
    * in the future.
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param animation
    *   Animation to send. Pass a file_id as String to send an animation that exists on the Telegram servers
    *   (recommended), pass an HTTP URL as a String for Telegram to get an animation from the Internet, or upload a new
    *   animation using multipart/form-data.
    *   [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]
    * @return
    *   [[SendAnimation]] builder
    */
  def sendAnimation(chatId: String, animation: FileDescriptor): SendAnimation

  /** Use this method to send audio files, if you want Telegram clients to display the file as a playable voice message.
    * For this to work, your audio must be in an .OGG file encoded with OPUS (other formats may be sent as [[Audio]] or
    * [[Document]]). On success, the sent [[Message]] is returned. Bots can currently send voice messages of up to 50 MB
    * in size, this limit may be changed in the future.
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param voice
    *   Audio file to send. Pass a file_id as String to send a file that exists on the Telegram servers (recommended),
    *   pass an HTTP URL as a String for Telegram to get a file from the Internet, or upload a new one using
    *   multipart/form-data. [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]
    * @return
    *   [[SendVoice]] builder
    */
  def sendVoice(chatId: String, voice: FileDescriptor): SendVoice

  /** As of [[https://telegram.org/blog/video-messages-and-telescope v.4.0]], Telegram clients support rounded square
    * MPEG4 videos of up to 1 minute long. Use this method to send video messages. On success, the sent [[Message]] is
    * returned.
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param videoNote
    *   Video note to send. Pass a file_id as String to send a video note that exists on the Telegram servers
    *   (recommended) or upload a new video using multipart/form-data.
    *   [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]. Sending video notes by
    *   a URL is currently unsupported
    * @return
    *   [[SendVideoNote]] builder
    */
  def sendVideoNote(chatId: String, videoNote: FileDescriptor): SendVideoNote

  /** Use this method to send a group of photos, videos, documents or audios as an album. Documents and audio files can
    * be only grouped in an album with messages of the same type. On success, an array of [[Message]] that were sent is
    * returned.
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param media
    *   A JSON-serialized array describing messages to be sent, must include 2-10 items
    * @return
    *   [[SendMediaGroup]] builder
    */
  def sendMediaGroup(chatId: String, media: Seq[InputMedia]): SendMediaGroup

  /** Use this method to send point on the map. On success, the sent [[Message]] is returned.
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param latitude
    *   Latitude of the location
    * @param longitude
    *   Longitude of the location
    * @return
    *   [[SendLocation]] builder
    */
  def sendLocation(chatId: String, latitude: Double, longitude: Double): SendLocation

  /** Use this method to edit live location messages. A location can be edited until its live_period expires or editing
    * is explicitly disabled by a call to [[StopMessageLiveLocation]]. On success, if the edited message is not an
    * inline message, the edited [[Message]] is returned, otherwise ''True'' is returned.
    *
    * @param latitude
    *   Latitude of new location
    * @param longitude
    *   Longitude of new location
    * @return
    *   [[EditMessageLiveLocation]] builder
    */
  def editMessageLiveLocation(latitude: Double, longitude: Double): EditMessageLiveLocation

  /** Use this method to stop updating a live location message before ''live_period'' expires. On success, if the
    * message is not an inline message, the edited [[Message]] is returned, otherwise ''True'' is returned.
    * @return
    *   [[StopMessageLiveLocation]] builder
    */
  def stopMessageLiveLocation: StopMessageLiveLocation

  /** Use this method to send information about a venue. On success, the sent [[Message]] is returned.
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param latitude
    *   Latitude of the venue
    * @param longitude
    *   Longitude of the venue
    * @param title
    *   Name of the venue
    * @param address
    *   Address of the venue
    * @return
    *   [[SendVenue]] builder
    */
  def sendVenue(chatId: String, latitude: Double, longitude: Double, title: String, address: String): SendVenue

  /** Use this method to send phone contacts. On success, the sent [[Message]] is returned.
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param phoneNumber
    *   Contact's phone number
    * @param firstName
    *   Contact's first name
    * @return
    *   [[SendContact]] builder
    */
  def sendContact(chatId: String, phoneNumber: String, firstName: String): SendContact

  /** Use this method to send a native poll. On success, the sent [[Message]] is returned.
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param question
    *   Poll question, 1-300 characters
    * @param options
    *   A JSON-serialized list of answer options, 2-10 strings 1-100 characters each
    * @return
    *   [[SendPoll]] builder
    */
  def sendPoll(chatId: String, question: String, options: Seq[String]): SendPoll

  /** Use this method to send an animated emoji that will display a random value. On success, the sent [[Message]] is
    * returned.
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @return
    *   [[SendDice]] builder
    */
  def sendDice(chatId: String): SendDice

  /** Use this method when you need to tell the user that something is happening on the bot's side. The status is set
    * for 5 seconds or less (when a message arrives from your bot, Telegram clients clear its typing status). Returns
    * ''True'' on success.
    *
    * Example: The [[https://t.me/imagebot ImageBot]] needs some time to process a request and upload the image. Instead
    * of sending a text message along the lines of “Retrieving image, please wait…”, the bot may use [[SendChatAction]]
    * with ''action = upload_photo''. The user will see a “sending photo” status for the bot.
    *
    * We only recommend using this method when a response from the bot will take a noticeable amount of time to arrive.
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param action
    *   Type of action to broadcast. Choose one, depending on what the user is about to receive: typing for
    *   [[https://core.telegram.org/bots/api#sendmessage text messages]], upload_photo for
    *   [[https://core.telegram.org/bots/api#sendphoto photos]], record_video or upload_video for
    *   [[https://core.telegram.org/bots/api#sendvideo videos]], record_voice or upload_voice for
    *   [[https://core.telegram.org/bots/api#sendvoice voice notes]], upload_document for
    *   [[https://core.telegram.org/bots/api#senddocument general files]], choose_sticker for
    *   [[https://core.telegram.org/bots/api#sendsticker stickers]], find_location for
    *   [[https://core.telegram.org/bots/api#sendlocation location data]], record_video_note or upload_video_note for
    *   [[https://core.telegram.org/bots/api#sendvideonote video notes]].
    *
    * @return
    *   [[SendChatAction]] builder
    */
  def sendChatAction(chatId: String, action: ChatAction): SendChatAction

  /** Use this method to get a list of profile pictures for a user. Returns a [[UserProfilePhotos]] object.
    * @param userId
    *   Unique identifier of the target user
    * @return
    *   [[GetUserProfilePhotos]] builder
    */
  def getUserProfilePhotos(userId: Long): GetUserProfilePhotos

  /** Use this method to get basic information about a file and prepare it for downloading. For the moment, bots can
    * download files of up to 20MB in size. On success, a [[File]] object is returned. The file can then be downloaded
    * via the link [[https://api.telegram.org/file/bot<token>/<file_path>]], where <file_path> is taken from the
    * response. It is guaranteed that the link will be valid for at least 1 hour. When the link expires, a new one can
    * be requested by calling [[GetFile]] again.
    *
    * @param fileId
    *   File identifier to get information about
    * @return
    *   [[GetFile]] builder
    */
  def getFile(fileId: String): GetFile

  /** Use this method to ban a user in a group, a supergroup or a channel. In the case of supergroups and channels, the
    * user will not be able to return to the chat on their own using invite links, etc., unless
    * [[https://core.telegram.org/bots/api#unbanchatmember unbanned]] first. The bot must be an administrator in the
    * chat for this to work and must have the appropriate administrator rights. Returns ''True'' on success.
    * @param chatId
    *   Unique identifier for the target group or username of the target supergroup or channel (in the format
    *   \@channelusername)
    * @param userId
    *   Unique identifier of the target user
    *
    * @return
    *   [[BanChatMember]] builder
    */
  def banChatMember(chatId: String, userId: Long): BanChatMember

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
  def unbanChatMember(chatId: String, userId: Long): UnbanChatMember

  /** Use this method to restrict a user in a supergroup. The bot must be an administrator in the supergroup for this to
    * work and must have the appropriate administrator rights. Pass ''True'' for all permissions to lift restrictions
    * from a user. Returns ''True'' on success.
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @param userId
    *   Unique identifier of the target user
    * @param permissions
    *   A JSON-serialized object for new user permissions
    * @return
    *   [[RestrictChatMember]] builder
    */
  def restrictChatMember(chatId: String, userId: Long, permissions: ChatPermissions): RestrictChatMember

  /** Use this method to promote or demote a user in a supergroup or a channel. The bot must be an administrator in the
    * chat for this to work and must have the appropriate administrator rights. Pass ''False'' for all boolean
    * parameters to demote a user. Returns ''True'' on success.
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @param userId
    *   Unique identifier of the target user
    * @return
    *   [[PromoteChatMember]] builder
    */
  def promoteChatMember(chatId: String, userId: Long): PromoteChatMember

  /** Use this method to set a custom title for an administrator in a supergroup promoted by the bot. Returns ''True''
    * on success.
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @param userId
    *   Unique identifier of the target user
    * @param customTitle
    *   New custom title for the administrator; 0-16 characters, emoji are not allowed
    * @return
    *   [[SetChatAdministratorCustomTitle]] builder
    */
  def setChatAdministratorCustomTitle(
    chatId: String,
    userId: Long,
    customTitle: String
  ): SetChatAdministratorCustomTitle

  /** Use this method to ban a channel chat in a supergroup or a channel. Until the chat is
    * [[https://core.telegram.org/bots/api#unbanchatsenderchat unbanned]], the owner of the banned chat won't be able to
    * send messages on behalf of any of their channels. The bot must be an administrator in the supergroup or channel
    * for this to work and must have the appropriate administrator rights. Returns ''True'' on success.
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @param senderChatId
    *   Unique identifier of the target sender chat
    * @return
    *   [[BanChatSenderChat]] builder
    */
  def banChatSenderChat(chatId: String, senderChatId: Long): BanChatSenderChat

  /** Use this method to unban a previously banned channel chat in a supergroup or channel. The bot must be an
    * administrator for this to work and must have the appropriate administrator rights. Returns ''True'' on success.
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @param senderChatId
    *   Unique identifier of the target sender chat
    * @return
    *   [[UnbanChatSenderChat]] builder
    */
  def unbanChatSenderChat(chatId: String, senderChatId: Long): UnbanChatSenderChat

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

  /** Use this method to generate a new primary invite link for a chat; any previously generated primary link is
    * revoked. The bot must be an administrator in the chat for this to work and must have the appropriate administrator
    * rights. Returns the new invite link as ''String'' on success.
    *
    * Note: Each administrator in a chat generates their own invite links. Bots can't use invite links generated by
    * other administrators. If you want your bot to work with invite links, it will need to generate its own link using
    * [[ExportChatInviteLink]] or by calling the [[GetChat]] method. If your bot needs to generate a new primary invite
    * link replacing its previous one, use [[ExportChatInviteLink]] again.
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @return
    *   [[ExportChatInviteLink]] builder
    */
  def exportChatInviteLink(chatId: String): ExportChatInviteLink

  /** Use this method to create an additional invite link for a chat. The bot must be an administrator in the chat for
    * this to work and must have the appropriate administrator rights. The link can be revoked using the method
    * [[https://core.telegram.org/bots/api#revokechatinvitelink revokeChatInviteLink]]. Returns the new invite link as
    * [[https://core.telegram.org/bots/api#chatinvitelink ChatInviteLink]] object.
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @return
    *   [[CreateChatInviteLink]] builder
    */
  def createChatInviteLink(chatId: String): CreateChatInviteLink

  /** Use this method to edit a non-primary invite link created by the bot. The bot must be an administrator in the chat
    * for this to work and must have the appropriate administrator rights. Returns the edited invite link as a
    * [[https://core.telegram.org/bots/api#chatinvitelink ChatInviteLink]] object.
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param inviteLink
    *   The invite link to edit
    * @return
    *   [[EditChatInviteLink]] builder
    */
  def editChatInviteLink(chatId: String, inviteLink: String): EditChatInviteLink

  /** Use this method to decline a chat join request. The bot must be an administrator in the chat for this to work and
    * must have the can_invite_users administrator right. Returns True on success.
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param userId
    *   Unique identifier of the target user
    * @return
    *   [[DeclineChatJoinRequest]] builder
    */
  def declineChatJoinRequest(chatId: String, userId: Long): DeclineChatJoinRequest

  /** Use this method to revoke an invite link created by the bot. If the primary link is revoked, a new link is
    * automatically generated. The bot must be an administrator in the chat for this to work and must have the
    * appropriate administrator rights. Returns the revoked invite link as
    * [[https://core.telegram.org/bots/api#chatinvitelink ChatInviteLink]] object.
    * @param chatId
    *   Unique identifier of the target chat or username of the target channel (in the format @channelusername)
    * @param inviteLink
    *   The invite link to revoke
    * @return
    *   [[RevokeChatInviteLink]] builder
    */
  def revokeChatInviteLink(chatId: String, inviteLink: String): RevokeChatInviteLink

  /** Use this method to approve a chat join request. The bot must be an administrator in the chat for this to work and
    * must have the can_invite_users administrator right. Returns True on success.
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param userId
    *   Unique identifier of the target user
    * @return
    *   [[ApproveChatJoinRequest]] builder
    */
  def approveChatJoinRequest(chatId: String, userId: Long): ApproveChatJoinRequest

  /** Use this method to set a new profile photo for the chat. Photos can't be changed for private chats. The bot must
    * be an administrator in the chat for this to work and must have the appropriate administrator rights. Returns True
    * on success.
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param photo
    *   New chat photo, uploaded using multipart/form-data
    * @return
    *   [[SetChatPhoto]] builder
    */
  def setChatPhoto(chatId: String, photo: FileDescriptor): SetChatPhoto

  /** Use this method to delete a chat photo. Photos can't be changed for private chats. The bot must be an
    * administrator in the chat for this to work and must have the appropriate administrator rights. Returns True on
    * success.
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @return
    *   [[DeleteChatPhoto]] builder
    */
  def deleteChatPhoto(chatId: String): DeleteChatPhoto

  /** Use this method to change the title of a chat. Titles can't be changed for private chats. The bot must be an
    * administrator in the chat for this to work and must have the appropriate administrator rights. Returns True on
    * success.
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param title
    *   New chat title, 1-128 characters
    * @return
    *   [[SetChatTitle]] builder
    */
  def setChatTitle(chatId: String, title: String): SetChatTitle

  /** Use this method to change the description of a group, a supergroup or a channel. The bot must be an administrator
    * in the chat for this to work and must have the appropriate administrator rights. Returns True on success.
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @return
    *   [[SetChatDescription]] builder
    */
  def setChatDescription(chatId: String): SetChatDescription

  /** Use this method to add a message to the list of pinned messages in a chat. If the chat is not a private chat, the
    * bot must be an administrator in the chat for this to work and must have the 'can_pin_messages' administrator right
    * in a supergroup or 'can_edit_messages' administrator right in a channel. Returns True on success.
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param messageId
    *   Identifier of a message to pin
    * @return
    *   [[PinChatMessage]] builder
    */
  def pinChatMessage(chatId: String, messageId: Long): PinChatMessage

  /** Use this method to remove a message from the list of pinned messages in a chat. If the chat is not a private chat,
    * the bot must be an administrator in the chat for this to work and must have the 'can_pin_messages' administrator
    * right in a supergroup or 'can_edit_messages' administrator right in a channel. Returns True on success.
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @return
    *   [[UnpinChatMessage]] builder
    */
  def unpinChatMessage(chatId: String): UnpinChatMessage

  /** Use this method to clear the list of pinned messages in a chat. If the chat is not a private chat, the bot must be
    * an administrator in the chat for this to work and must have the 'can_pin_messages' administrator right in a
    * supergroup or 'can_edit_messages' administrator right in a channel. Returns True on success.
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @return
    *   [[UnpinAllChatMessages]] builder
    */
  def unpinAllChatMessages(chatId: String): UnpinAllChatMessages

  /** Use this method for your bot to leave a group, supergroup or channel. Returns True on success.
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup or channel (in the format
    *   \@channelusername)
    * @return
    *   [[LeaveChat]] builder
    */
  def leaveChat(chatId: String): LeaveChat

  /** Use this method to get up to date information about the chat (current name of the user for one-on-one
    * conversations, current username of a user, group or channel, etc.). Returns a [[Chat]] object on success.
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup or channel (in the format
    *   \@channelusername)
    * @return
    *   [[GetChat]] builder
    */
  def getChat(chatId: String): GetChat

  /** Use this method to get a list of administrators in a chat, which aren't bots. Returns an Array of
    * [[https://core.telegram.org/bots/api#chatmember ChatMember]] objects.
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup or channel (in the format
    *   \@channelusername)
    * @return
    *   [[GetChatAdministrators]] builder
    */
  def getChatAdministrators(chatId: String): GetChatAdministrators

  /** Use this method to get the number of members in a chat. Returns Int on success.
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup or channel (in the format
    *   \@channelusername)
    * @return
    *   [[GetChatMemberCount]] builder
    */
  def getChatMemberCount(chatId: String): GetChatMemberCount

  /** Use this method to get information about a member of a chat. Returns a
    * [[https://core.telegram.org/bots/api#chatmember ChatMember]] object on success.
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup or channel (in the format
    *   \@channelusername)
    * @param userId
    *   Unique identifier of the target user
    * @return
    *   [[GetChatMember]] builder
    */
  def getChatMember(chatId: String, userId: Long): GetChatMember

  /** Use this method to set a new group sticker set for a supergroup. The bot must be an administrator in the chat for
    * this to work and must have the appropriate administrator rights. Use the field can_set_sticker_set optionally
    * returned in [[https://core.telegram.org/bots/api#getchat getChat]] requests to check if the bot can use this
    * method. Returns True on success.
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @param stickerSetName
    *   Name of the sticker set to be set as the group sticker set
    * @return
    *   [[SetChatStickerSet]] builder
    */
  def setChatStickerSet(chatId: String, stickerSetName: String): SetChatStickerSet

  /** Use this method to delete a group sticker set from a supergroup. The bot must be an administrator in the chat for
    * this to work and must have the appropriate administrator rights. Use the field can_set_sticker_set optionally
    * returned in [[https://core.telegram.org/bots/api#getchat getChat]] requests to check if the bot can use this
    * method. Returns True on success.
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @return
    *   [[DeleteChatStickerSet]] builder
    */
  def deleteChatStickerSet(chatId: String): DeleteChatStickerSet

  /** Use this method to get custom emoji stickers, which can be used as a forum topic icon by any user. Requires no
    * parameters. Returns an Array of [[https://core.telegram.org/bots/api#sticker Sticker]] objects.
    *
    * @return
    *   [[GetForumTopicIconStickers]] builder
    */
  def getForumTopicIconStickers: GetForumTopicIconStickers

  /** Use this method to create a topic in a forum supergroup chat. The bot must be an administrator in the chat for
    * this to work and must have the can_manage_topics administrator rights. Returns information about the created topic
    * as a [[https://core.telegram.org/bots/api#forumtopic ForumTopic]] object.
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @param name
    *   Topic name, 1-128 characters
    * @return
    *   [[CreateForumTopic]] builder
    */
  def createForumTopic(chatId: String, name: String): CreateForumTopic

  /** Use this method to edit name and icon of a topic in a forum supergroup chat. The bot must be an administrator in
    * the chat for this to work and must have can_manage_topics administrator rights, unless it is the creator of the
    * topic. Returns True on success.
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @param messageThreadId
    *   Unique identifier for the target message thread of the forum topic
    * @param name
    *   New topic name, 1-128 characters
    * @param iconCustomEmojiId
    *   New unique identifier of the custom emoji shown as the topic icon. Use
    *   [[https://core.telegram.org/bots/api#getforumtopiciconstickers getForumTopicIconStickers]] to get all allowed
    *   custom emoji identifiers.
    * @return
    *   [[EditForumTopic]] builder
    */
  def editForumTopic(chatId: String, messageThreadId: Long, name: String, iconCustomEmojiId: String): EditForumTopic

  /** Use this method to close an open topic in a forum supergroup chat. The bot must be an administrator in the chat
    * for this to work and must have the can_manage_topics administrator rights, unless it is the creator of the topic.
    * Returns True on success.
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @param messageThreadId
    *   Unique identifier for the target message thread of the forum topic
    * @return
    *   [[CloseForumTopic]] builder
    */
  def closeForumTopic(chatId: String, messageThreadId: Long): CloseForumTopic

  /** Use this method to reopen a closed topic in a forum supergroup chat. The bot must be an administrator in the chat
    * for this to work and must have the can_manage_topics administrator rights, unless it is the creator of the topic.
    * Returns True on success.
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @param messageThreadId
    *   Unique identifier for the target message thread of the forum topic
    * @return
    *   [[ReopenForumTopic]] builder
    */
  def reopenForumTopic(chatId: String, messageThreadId: Long): ReopenForumTopic

  /** Use this method to delete a forum topic along with all its messages in a forum supergroup chat. The bot must be an
    * administrator in the chat for this to work and must have the can_delete_messages administrator rights. Returns
    * True on success.
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @param messageThreadId
    *   Unique identifier for the target message thread of the forum topic
    * @return
    *   [[DeleteForumTopic]] builder
    */
  def deleteForumTopic(chatId: String, messageThreadId: Long): DeleteForumTopic

  /** Use this method to clear the list of pinned messages in a forum topic. The bot must be an administrator in the
    * chat for this to work and must have the can_pin_messages administrator right in the supergroup. Returns True on
    * success.
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @param messageThreadId
    *   Unique identifier for the target message thread of the forum topic
    * @return
    *   [[UnpinAllForumTopicMessages]] builder
    */
  def unpinAllForumTopicMessages(chatId: String, messageThreadId: Long): UnpinAllForumTopicMessages

  /** Use this method to send answers to callback queries sent from
    * [[https://core.telegram.org/bots/features#inline-keyboards inline keyboards]]. The answer will be displayed to the
    * user as a notification at the top of the chat screen or as an alert. On success, True is returned.
    *
    * Alternatively, the user can be redirected to the specified Game URL. For this option to work, you must first
    * create a game for your bot via [[https://t.me/botfather @BotFather]] and accept the terms. Otherwise, you may use
    * links like `t.me/your_bot?start=XXXX` that open your bot with a parameter.
    *
    * @param callbackQueryId
    *   Unique identifier for the query to be answered
    * @return
    *   [[AnswerCallbackQuery]] builder
    */
  def answerCallbackQuery(callbackQueryId: String): AnswerCallbackQuery

  /** Use this method to change the list of the bot's commands. See
    * [[https://core.telegram.org/bots/features#commands this manual]] for more details about bot commands. Returns True
    * on success.
    *
    * @param commands
    *   A JSON-serialized list of bot commands to be set as the list of the bot's commands. At most 100 commands can be
    *   specified.
    * @return
    *   [[AnswerCallbackQuery]] builder
    */
  def setMyCommands(commands: Seq[BotCommand]): SetMyCommands

  /** Use this method to delete the list of the bot's commands for the given scope and user language. After deletion,
    * [[https://core.telegram.org/bots/api#determining-list-of-commands higher level commands]] will be shown to
    * affected users. Returns True on success.
    *
    * @return
    *   [[DeleteMyCommands]] builder
    */
  def deleteMyCommands: DeleteMyCommands

  /** Use this method to get the current list of the bot's commands for the given scope and user language. Returns an
    * Array of [[https://core.telegram.org/bots/api#botcommand BotCommand]] objects. If commands aren't set, an empty
    * list is returned.
    *
    * @return
    *   [[GetMyCommands]] builder
    */
  def getMyCommands: GetMyCommands

  /** Use this method to change the bot's menu button in a private chat, or the default menu button. Returns True on
    * success.
    *
    * @return
    *   [[SetChatMenuButton]] builder
    */
  def setChatMenuButton: SetChatMenuButton

  /** Use this method to get the current value of the bot's menu button in a private chat, or the default menu button.
    * Returns [[https://core.telegram.org/bots/api#menubutton MenuButton]] on success.
    *
    * @return
    *   [[GetChatMenuButton]] builder
    */
  def getChatMenuButton: GetChatMenuButton

  /** Use this method to change the default administrator rights requested by the bot when it's added as an
    * administrator to groups or channels. These rights will be suggested to users, but they are are free to modify the
    * list before adding the bot. Returns True on success.
    *
    * @return
    *   [[SetMyDefaultAdministratorRights]] builder
    */
  def setMyDefaultAdministratorRights: SetMyDefaultAdministratorRights

  /** Use this method to get the current default administrator rights of the bot. Returns
    * [[https://core.telegram.org/bots/api#chatadministratorrights ChatAdministratorRights]] on success.
    *
    * @return
    *   [[GetMyDefaultAdministratorRights]] builder
    */
  def getMyDefaultAdministratorRights: GetMyDefaultAdministratorRights
}
