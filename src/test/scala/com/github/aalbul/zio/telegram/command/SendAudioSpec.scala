package com.github.aalbul.zio.telegram.command

import com.github.aalbul.zio.telegram.command.FileDescriptor.{idDescriptor, pathDescriptor}
import com.github.aalbul.zio.telegram.command.MultipartBody.{filePart, stringPart}
import com.github.aalbul.zio.telegram.domain.{Message, ParseModes}
import com.github.aalbul.zio.telegram.test.BaseSpec

class SendAudioSpec extends BaseSpec {
  trait Scope {
    val command: Command[Message] =
      SendAudio
        .of(chatId = "9921", audio = idDescriptor("980001"))
        .withCaption("cool music")
        .withParseMode(ParseModes.HTML)
        .withCaptionEntities(Seq(messageEntity1))
        .withDuration(1533)
        .withPerformer("John Wick")
        .withTitle("Song 1")
        .withThumb(pathDescriptor("/tmp/1.png"))
        .withDisableNotification(true)
        .withProtectContent(false)
        .withReplyToMessageId(50)
        .withAllowSendingWithoutReply(true)
        .withReplyMarkup(forceReplyMarkup1)
  }

  "SendAudio" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "sendAudio"
      }
    }

    "parameters" should {
      "represent parameters as form data" in new Scope {
        command.parameters shouldBe MultipartBody.of(
          stringPart("chat_id", "9921"),
          stringPart("audio", "980001"),
          stringPart("caption", "cool music"),
          stringPart("parse_mode", "HTML"),
          stringPart("caption_entities", jsonResourceString("json/command/caption-entities.json")),
          stringPart("duration", "1533"),
          stringPart("performer", "John Wick"),
          stringPart("title", "Song 1"),
          filePart("thumb", "/tmp/1.png"),
          stringPart("disable_notification", "true"),
          stringPart("protect_content", "false"),
          stringPart("reply_to_message_id", "50"),
          stringPart("allow_sending_without_reply", "true"),
          stringPart("reply_markup", jsonResourceString("json/command/reply-markup.json"))
        )
      }
    }
  }
}
