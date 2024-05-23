import scalanative.build.Mode

enablePlugins(ScalaNativePlugin)

name := "gears"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "3.4.1"
libraryDependencies ++= {
  Seq(
    "ch.epfl.lamp" %%% "gears" % "0.2.0",
    "com.lihaoyi" %% "ujson" % "3.3.1",
    "org.scalatest" %% "scalatest" % "3.2.18" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
nativeConfig ~= {
  _.withMode(Mode.releaseFast)
}