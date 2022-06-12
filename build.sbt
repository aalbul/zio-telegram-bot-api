import Dependencies._

ThisBuild / scalaVersion     := "2.13.8"
ThisBuild / version          := "0.1.0"
ThisBuild / organization     := "com.github.aalbul"
ThisBuild / organizationName := "zio-telegram-bot-api"

ThisBuild / scalacOptions ++= Seq("-Ymacro-annotations", "-deprecation", "-unchecked")

lazy val root = (project in file("."))
  .settings(
    name := "zio-telegram-bot-api",
    libraryDependencies ++= Seq(
      zio,
      zioStreams,
      circeGeneric,
      circeParser,
      circeGenericExtras,
      sttpAsyncHttpZio,
      scalatest        % Test,
      scalatestMockito % Test
    ),
    scalacOptions ++= Seq("-Xsource:3")
  )
