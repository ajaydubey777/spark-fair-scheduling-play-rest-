lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """play-scala-hello-world-tutorial""",
    organization := "com.example",
    version := "1.0-SNAPSHOT",
   // scalaVersion := "2.13.1",
    libraryDependencies ++= Seq(
      guice,
      "org.scala-lang" % "scala-library" % "2.11.12",
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
      "com.fasterxml.jackson.core" % "jackson-core" % "2.10.3",
      "com.fasterxml.jackson.core" % "jackson-databind" % "2.10.3",
      "com.fasterxml.jackson.core" % "jackson-annotations" % "2.10.3",
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.10.3",
      "org.apache.spark" %% "spark-core" % "2.4.5",
      "org.apache.spark" %% "spark-sql" % "2.4.5"
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-Xfatal-warnings"
    )
  )
