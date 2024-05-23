package objektwerks

import gears.async.{Async, Future}
import gears.async.default.given

@main
def runFutureApp: Unit =
  Async.blocking:
    val joke1 = Future( getJoke() )
    val joke2 = Future( getJoke() )
    val jokes = Seq(joke1, joke2).awaitAll
    jokes.foreach(println)