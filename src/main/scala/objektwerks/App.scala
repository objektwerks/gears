package objektwerks

import gears.async.{Async, Future}
import gears.async.default.given

@main
def runApp: Unit =
  futures()


private def futures(): Unit =
  Async.blocking:
    Seq(
      Future( getJoke() ),
      Future( getJoke() ),
      Future( getJoke() )
    )
    .awaitAll
    .foreach(println)