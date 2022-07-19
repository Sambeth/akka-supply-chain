ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.1"

val AkkaVersion = "2.6.19"
val ScalaTestVersion = "3.2.12"

lazy val root = (project in file("."))
  .settings(
    name := "akka-supply-chain",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
      "com.typesafe.akka" %% "akka-actor-testkit-typed" % AkkaVersion % Test,
      "org.scalatest" %% "scalatest" % ScalaTestVersion % Test,
      "ch.qos.logback" % "logback-classic" % "1.2.11" % Test
    )
  )
