name := """hashtag-extractor"""
organization := "io.iaa"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.8"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.1" % Test
libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "2.6.0"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "io.iaa.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "io.iaa.binders._"
