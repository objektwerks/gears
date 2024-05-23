package objektwerks

import gears.async.Async
import gears.async.default.given

@main
def runAsyncApp: Unit =
  Async.blocking:
    val joke = getJoke()
    println(joke)