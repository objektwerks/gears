package objektwerks

import gears.async.Async

import scala.annotation.tailrec
import scala.io.{Codec, Source}
import scala.util.Using

@tailrec
def factorial(n: Int, acc: Int = 1): Int =
  n match
    case i if i < 1 => acc
    case _ => factorial(n - 1, acc * n)

@tailrec
def reverse[A](list: List[A], acc: List[A] = List.empty[A]): List[A] =
  list match
    case Nil => acc
    case head :: tail => reverse(tail, head :: acc)

def getJoke()(using Async): String =
  Using( Source.fromURL("https://api.chucknorris.io/jokes/random", Codec.UTF8.name) ) { 
    source => s"* ${parseJson( source.mkString )}"
  }.getOrElse("Chuck Norris is taking a power nap! Come back in a few nanoseconds!")

private def parseJson(json: String): String = ujson.read(json)("value").str