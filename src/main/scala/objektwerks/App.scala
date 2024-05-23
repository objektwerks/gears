package objektwerks

import gears.async.{Async, Future, Retry, withTimeout}
import gears.async.Retry.Delay
import gears.async.default.given

import scala.concurrent.duration.DurationInt

@main
def runApp: Unit =
  futures()
  select()
  timeout()
  retry()

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
        s"factorial of 11 = ${n.get}",
      list.handle: l =>
        s"reversed List(3, 2, 1) = ${l.get}"
    )
    println(s"* $winner")

private def timeout(): Unit =
  Async.blocking:
    withTimeout(60.seconds):
      println( getJoke() )

private def retry(): Unit =
  Async.blocking:
    Retry
      .untilSuccess
      .withMaximumFailures(2)
      .withDelay(Delay.constant(3.seconds)):
        println( getJoke() )