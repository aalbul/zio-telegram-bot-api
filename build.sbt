import Dependencies._

import ReleaseTransformations._

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
