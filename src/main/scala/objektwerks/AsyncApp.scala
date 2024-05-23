package objektwerks

import gears.async.Async
import gears.async.default.given

@main
def runAsyncApp: Unit =e
  Async.blocking:
    getJoke()