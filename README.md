# zio-telegram-bot-api

## API

Currently supported version is: 5.4

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

Make sure that you have PGP keys:

```shell
gpg --keyserver keyserver.ubuntu.com --recv-keys CE9AA526FAF075A4057AAEBA45BB1D604C232B8D
```

The actual release can be done using `sbt release` command
