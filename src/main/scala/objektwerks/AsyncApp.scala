package objektwerks

import gears.async.Async

@main
def runParApp: Unit =
  Async.blocking:
    joke()