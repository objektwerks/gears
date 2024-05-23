package objektwerks

import gears.async.Async
import gears.async.default.given

@main
def runParApp: Unit =
  Async.blocking:
    getJoke()