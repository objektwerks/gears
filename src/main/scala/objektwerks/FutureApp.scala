package objektwerks

import gears.async.{Async, Future}
import gears.async.default.given

@main
def runFutureApp: Unit =
  Async.blocking:
    val joke1 = Future( getJoke() )
    val joke2 = Future( getJoke() )
    val (j1, j2) = joke1.zip(joke2).await
    println(j1)
    println(j2)