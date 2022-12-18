import sbt._

trait DependencyVersions {
  protected val zioVersion            = "2.0.4"
  protected val sttpVersion           = "3.8.3"
  protected val scalatestVersion      = "3.2.14"
  protected val scalatestCirceVersion = "0.2.5"
  protected val mockitoScalaVersion   = "1.17.12"
  protected val jsoniterVersion       = "2.19.1"
  protected val enumeratumVersion     = "1.7.2"
  protected val catsVersion           = "2.9.0"
}

object Dependencies extends DependencyVersions {
  lazy val zio                 = "dev.zio"                               %% "zio"                   % zioVersion
  lazy val zioStreams          = "dev.zio"                               %% "zio-streams"           % zioVersion
  lazy val sttpZio             = "com.softwaremill.sttp.client3"         %% "zio"                   % sttpVersion
  lazy val enumeratum          = "com.beachape"                          %% "enumeratum"            % enumeratumVersion
  lazy val catsCore            = "org.typelevel"                         %% "cats-core"             % catsVersion
  lazy val scalatest           = "org.scalatest"                         %% "scalatest"             % scalatestVersion
  lazy val jsoniterScalaCore   = "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-core"   % jsoniterVersion
  lazy val jsoniterScalaMacros = "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % jsoniterVersion
  lazy val scalatestCirce = "com.stephenn" %% "scalatest-circe"         % scalatestCirceVersion
  lazy val mockitoScala   = "org.mockito"  %% "mockito-scala-scalatest" % mockitoScalaVersion
}
