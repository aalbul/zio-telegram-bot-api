import Dependencies._
import sbtrelease.ReleasePlugin.autoImport.ReleaseTransformations._

ThisBuild / name             := "zio-telegram-bot-api"
ThisBuild / scalaVersion     := "2.13.8"
ThisBuild / version          := "0.1.0"
ThisBuild / organization     := "io.github.aalbul"
ThisBuild / organizationName := "Nemesis"
ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/aalbul/zio-telegram-bot-api"),
    "scm:git@github.com:aalbul/zio-telegram-bot-api.git"
  )
)

ThisBuild / scalacOptions ++= Seq("-Ymacro-annotations", "-deprecation", "-unchecked")
ThisBuild / versionScheme := Some("early-semver")
ThisBuild / description   := "ZIO-Powered Telegram Bot Api for Scala"
ThisBuild / licenses := List(
  "MIT" -> new URL("https://raw.githubusercontent.com/aalbul/zio-telegram-bot-api/main/LICENSE")
)
ThisBuild / homepage := Some(url("https://github.com/aalbul/zio-telegram-bot-api"))

lazy val root = (project in file("."))
  .settings(
    name := "zio-telegram-bot-api",
    libraryDependencies ++= Seq(
      zio,
      zioStreams,
      sttpZio,
      enumeratum,
      catsCore,
      jsoniterScalaCore,
      jsoniterScalaMacros % "provided",
      scalatestCirce      % Test,
      scalatest           % Test,
      mockitoScala        % Test
    ),
    scalacOptions ++= Seq("-Xsource:3")
  )

ThisBuild / publishTo                 := sonatypePublishToBundle.value
ThisBuild / sonatypeRepository        := "https://s01.oss.sonatype.org/service/local"
ThisBuild / sonatypeCredentialHost    := "s01.oss.sonatype.org"
ThisBuild / publishConfiguration      := publishConfiguration.value.withOverwrite(true)
ThisBuild / publishLocalConfiguration := publishLocalConfiguration.value.withOverwrite(true)
ThisBuild / developers := List(
  Developer(
    id = "gorenuru",
    name = "Oleksandr Albul",
    email = "gorenuru@gmail.com",
    url = url("https://github.com/aalbul")
  )
)

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  releaseStepCommand("publishSigned"),
  releaseStepCommand("sonatypeBundleRelease"),
  setNextVersion,
  commitNextVersion,
  pushChanges
)
