package com.github.aalbul.zio.telegram.util

import com.pengrad.telegrambot.{Callback, TelegramBot, TelegramException}
import com.pengrad.telegrambot.request.BaseRequest
import com.pengrad.telegrambot.response.BaseResponse
import zio.{Task, ZIO}

import java.io.IOException

object Sugar {
  implicit class TelegramBotOps(bot: TelegramBot) {
    def executeRequest[T <: BaseRequest[T, R], R <: BaseResponse](request: T): Task[R] = {
      ZIO.async { callback =>
        bot.execute(
          request,
          new Callback[T, R] {
            override def onResponse(request: T, response: R): Unit = if (!response.isOk) {
              callback(
                ZIO.fail(
                  new TelegramException(
                    s"Request failed with error_code ${response.errorCode()} ${response.description()}",
                    response
                  )
                )
              )
            } else callback(ZIO.succeed(response))
            override def onFailure(request: T, e: IOException): Unit = callback(ZIO.fail(e))
          }
        )
      }
    }
  }
}
