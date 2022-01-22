package org.talgat.shakirov

import scala.annotation.tailrec

object ChangeSignTailRec {
  //The task is to count the number of sign changes in immutable Seq, 0 is not considered a sign change.
  // Loops (for, while, do) cannot be used in the task.
  // Example list(0, 1, -2) - 0+1-2 = -1 the sign changed once
  //life codding 10 minutes

  private def changeSign(list: Seq[Int]): Int = {

    def getSign(was: Int, is: Int): (Int, Boolean) = {
      if (was >= 0) {
        (was + is, was + is >= 0)
      } else {
        (was + is, was + is > 0)
      }
    }

    @tailrec
    def iter(list: Seq[Int], signSum: (Int, Int, Boolean)): (Int, Int, Boolean) = {
      list match {
        case Nil => signSum
        case _ =>
          val sign = getSign(signSum._1, list.head)
          val acc: Int = if (sign._2 != signSum._3) {
            signSum._2 + 1
          } else {
            signSum._2
          }
          iter(list.tail, (sign._1, acc, sign._2))
      }
    }

    iter(list, (0, 0, true))._2
  }

  def tailRecExample = {
    val testSeq = Seq(0, -1, 2, -3, 4, 8, -15, 2)
    println("Seq like that"+testSeq)
    printf("Sign changed = %d times", changeSign(testSeq))
  }
}
