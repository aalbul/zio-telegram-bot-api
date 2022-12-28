package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class BotCommandScopeDefaultSpec extends BaseSpec {
  "BotCommandScope" when {
    "encoder" should {
      "should encode BotCommandScopeDefault to json" in {
        writeToString(botCommandScope1) should matchJsonResource("json/model/bot-command-scope-default.json")
      }

      "should encode BotCommandScopeAllPrivateChats to json" in {
        writeToString(botCommandScope2) should matchJsonResource("json/model/bot-command-scope-all-private-chats.json")
      }

      "should encode BotCommandScopeAllGroupChats to json" in {
        writeToString(botCommandScope3) should matchJsonResource("json/model/bot-command-scope-all-group-chats.json")
      }

      "should encode BotCommandScopeAllChatAdministrators to json" in {
        writeToString(botCommandScope4) should matchJsonResource(
          "json/model/bot-command-scope-all-chat-administrators.json"
        )
      }

      "should encode BotCommandScopeChat to json" in {
        writeToString(botCommandScope5) should matchJsonResource("json/model/bot-command-scope-chat.json")
      }

      "should encode BotCommandScopeChatAdministrators to json" in {
        writeToString(botCommandScope6) should matchJsonResource(
          "json/model/bot-command-scope-chat-administrators.json"
        )
      }

      "should encode BotCommandScopeChatMember to json" in {
        writeToString(botCommandScope7) should matchJsonResource(
          "json/model/bot-command-scope-chat-member.json"
        )
      }
    }

    "decoder" should {
      "should decode BotCommandScopeDefault from json" in {
        jsonResourceAs[BotCommandScope]("json/model/bot-command-scope-default.json") shouldBe botCommandScope1
      }

      "should decode BotCommandScopeAllPrivateChats from json" in {
        jsonResourceAs[BotCommandScope]("json/model/bot-command-scope-all-private-chats.json") shouldBe botCommandScope2
      }

      "should decode BotCommandScopeAllGroupChats from json" in {
        jsonResourceAs[BotCommandScope]("json/model/bot-command-scope-all-group-chats.json") shouldBe botCommandScope3
      }

      "should decode BotCommandScopeAllChatAdministrators from json" in {
        jsonResourceAs[BotCommandScope](
          "json/model/bot-command-scope-all-chat-administrators.json"
        ) shouldBe botCommandScope4
      }

      "should decode BotCommandScopeChat from json" in {
        jsonResourceAs[BotCommandScope]("json/model/bot-command-scope-chat.json") shouldBe botCommandScope5
      }

      "should decode BotCommandScopeChatAdministrators from json" in {
        jsonResourceAs[BotCommandScope](
          "json/model/bot-command-scope-chat-administrators.json"
        ) shouldBe botCommandScope6
      }

      "should decode BotCommandScopeChatMember from json" in {
        jsonResourceAs[BotCommandScope](
          "json/model/bot-command-scope-chat-member.json"
        ) shouldBe botCommandScope7
      }
    }
  }
}
