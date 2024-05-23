package objektwerks

import gears.async.{Async, Future}
import gears.async.default.given

@main
def runApp: Unit =
  Async.blocking:
    Seq(
      Future( getJoke() ),
      Future( getJoke() )
    )
    .awaitAll
    .foreach(println)