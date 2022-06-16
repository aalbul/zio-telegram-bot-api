# zio-telegram-bot-api

## Releasing

To release you first need to configure your sbt sonatype credentials:

add to `~/.sbt/1.0/sonatype.sbt`:

```scala
credentials += Credentials(Path.userHome / ".sbt" / "sonatype_credentials")
```

Next create a file `~/.sbt/sonatype_credentials`:

```scala
realm=Sonatype Nexus Repository Manager
host=s01.oss.sonatype.org
user=<your username>
password=<your password>
```

The actual release can be done using `sbt release` command
