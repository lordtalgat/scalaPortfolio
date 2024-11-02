package org.talgat.shakirov

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}
import scala.util.Try

/**
 * For input Seq[Future[String]]
 * Output should be Future[(Seq[String], Seq[Throwable])] - result with sum of throws and good strings
 */


object Futures extends App {
  val run: Unit = {
    val fut = Seq(Future {
      "Test"
    }, Future {
      "Test2"
    },
      Future(throw new Exception("Error")))

    val fut2 = Future.fromTry(Try(10/0))
      .recover{
        case e: Exception =>
      e.getMessage
      }

    def recalc(fut: Seq[Future[String]]): Future[Seq[Either[Throwable, String]]] = {
      val ai = Future.traverse(fut) { el =>
        el.map { str =>
          Right(str)
        }.recover(ex => Left(ex))
      }

      ai
    }

    val res = recalc(fut)


    val total = res.map { b =>
      b.foldLeft(Seq.empty[String], Seq.empty[Throwable]) { case (acc, el) =>
        el match {
          case Left(ex) => (acc._1, acc._2 :+ ex)
          case Right(str) => (acc._1 :+ str, acc._2)
        }
      }
    }

    Await.ready(total, 1.seconds)
    println(total)
    Await.ready(fut2, 1.seconds)
    println(fut2)
  }
}
