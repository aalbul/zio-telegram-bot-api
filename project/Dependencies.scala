import sbt._

trait DependencyVersions {
  protected val zioVersion          = "2.0.0-RC6"
  protected val sttpVersion         = "3.6.2"
  protected val circeVersion        = "0.14.2"
  protected val scalatestVersion    = "3.2.12"
  protected val mockitoScalaVersion = "1.17.7"
}

object Dependencies extends DependencyVersions {
  lazy val zio                = "dev.zio"                       %% "zio"                           % zioVersion
  lazy val zioStreams         = "dev.zio"                       %% "zio-streams"                   % zioVersion
  lazy val circeParser        = "io.circe"                      %% "circe-parser"                  % circeVersion
  lazy val circeGeneric       = "io.circe"                      %% "circe-generic"                 % circeVersion
  lazy val circeGenericExtras = "io.circe"                      %% "circe-generic-extras"          % circeVersion
  lazy val sttpAsyncHttpZio   = "com.softwaremill.sttp.client3" %% "async-http-client-backend-zio" % sttpVersion
  lazy val scalatest          = "org.scalatest"                 %% "scalatest"                     % scalatestVersion
  lazy val mockitoScala       = "org.mockito"                   %% "mockito-scala-scalatest"       % mockitoScalaVersion
}
