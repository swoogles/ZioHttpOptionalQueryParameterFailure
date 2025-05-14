ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.20"

lazy val root = (project in file("."))
  .settings(
    name := "OptionalQueryCodecFailure",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio-http" % "3.2.0",
    )
  )
