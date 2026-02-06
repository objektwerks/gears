name := "gears"
organization := "objektwerks"
version := "0.4-SNAPSHOT"
scalaVersion := "3.8.2-RC1"
libraryDependencies ++= {
  Seq(
    "ch.epfl.lamp" %% "gears" % "0.2.0",
    "com.lihaoyi" %% "ujson" % "4.4.2"
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
