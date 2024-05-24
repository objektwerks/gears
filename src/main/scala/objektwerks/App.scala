package objektwerks

import gears.async.{Async, AsyncOperations, Future, Retry, SyncChannel, withTimeout}
import gears.async.Channel.Closed
import gears.async.Retry.Delay
import gears.async.default.given

import scala.concurrent.duration.DurationInt

@main
def runApp: Unit =
  future()
  futures()
  select()
  timeout()
  retry()
  channel()

private def future(): Unit =
  Async.blocking:
    val joke = Future( getJoke() ).await
    println(joke)

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
    val list = Future( reverse( List(3, 2, 1) ) )
    val number = Future( factorial(11) )
    val winner = Async.select(
      list.handle: l =>
         // always wins! so is it a race? :)
        s"reversed List(3, 2, 1) = ${l.get}",
      number.handle: n =>
        s"factorial of 11 = ${n.get}"
    )
    println(s"* $winner")

private def timeout(): Unit =
  Async.blocking:
    withTimeout(10.seconds):
      println( getJoke() )

private def retry(): Unit =
  Async.blocking:
    Retry
      .untilSuccess
      .withMaximumFailures(2)
      .withDelay( Delay.constant(3.seconds) ):
        println( getJoke() )

private def channel(): Unit =
  Async.blocking:
    val channel = SyncChannel[Int]()

    val send = Future:
      AsyncOperations.sleep(3.seconds)
      1

    val read = Future:
      channel.read().right.get

    Async.select(
      send.handle: f =>
        println(s"factorial $f"),

      channel.sendSource( factorial(11) ).handle:
        case Left(Closed) => println("* channel closed!")
        case Right(()) => println(s"* channel read factorial of 11 = ${read.await}")
    )