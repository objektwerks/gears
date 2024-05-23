package objektwerks

import gears.async.{Async, Future}
import gears.async.default.given

@main
def runApp: Unit =
  futures()
  select()

private def futures(): Unit =
  Async.blocking:
    Seq(
      Future( getJoke() ),
      Future( getJoke() ),
      Future( getJoke() )
    )
    .awaitAll
    .foreach(println)

private def select(): Unit =
  Async.blocking:
    val number = Future( factorial(9) )
    val list = Future( reverse( List(3, 2, 1) ) )

    val winner = Async.select(
      number.handle: n =>
        s"factorial $n",
      list.handle: l =>
        s"reversed $l"
    )
    println(winner)