package objektwerks

import gears.async.{Async, Future, withTimeout}
import gears.async.default.given

import scala.concurrent.duration.DurationInt

@main
def runApp: Unit =
  futures()
  select()
  timeout()

private def futures(): Unit =
  Async.blocking:
    Seq(
      Future( getJoke() ),
      Future( getJoke() )
    )
    .awaitAll
    .foreach(println)

private def select(): Unit =
  Async.blocking:
    val number = Future( factorial(11) )
    val list = Future( reverse( List(3, 2, 1) ) )
    val winner = Async.select(
      number.handle: n =>
        s"factorial ${n.get}",
      list.handle: l =>
        s"reversed ${l.get}"
    )
    println(s"* $winner")

private def timeout(): Unit =
  Async.blocking:
    withTimeout(60.seconds):
      println( getJoke() )