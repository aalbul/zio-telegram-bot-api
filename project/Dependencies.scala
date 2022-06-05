import sbt._

trait DependencyVersions {
  protected val zioVersion                = "2.0.0-RC6"
  protected val javaTelegramBotApiVersion = "6.0.1"
}

object Dependencies extends DependencyVersions {
  lazy val zio            = "dev.zio"           %% "zio"                   % zioVersion
  lazy val zioStreams     = "dev.zio"           %% "zio-streams"           % zioVersion
  lazy val telegramBotApi = "com.github.pengrad" % "java-telegram-bot-api" % javaTelegramBotApiVersion
}
