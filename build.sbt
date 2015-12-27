lazy val fastcgi = (project in file("."))
  .enablePlugins(GitVersioning)
  .settings(
    licenses +=("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html")),
    organization := "com.scalawilliam",
    name := "scala-fastcgi-client",
    scalaVersion := "2.11.7",
    libraryDependencies ++= Seq(
      "net.sf.jfastcgi" % "jfastcgi" % "2.2",
      "org.scalatest" %% "scalatest" % "2.2.5" % "test",
      "org.scalactic" %% "scalactic" % "2.2.5",
      "commons-pool" % "commons-pool" % "1.6",
      "ch.qos.logback" % "logback-classic" % "1.1.3" % "test",
      "org.slf4j" % "slf4j-api" % "1.7.13" % "test"
    )
  )
