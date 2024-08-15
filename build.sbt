name := "gears"
organization := "objektwerks"
version := "0.4-SNAPSHOT"
scalaVersion := "3.5.0"
libraryDependencies ++= {
  Seq(
    "ch.epfl.lamp" %% "gears" % "0.2.0",
    "com.lihaoyi" %% "ujson" % "4.0.0"
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
