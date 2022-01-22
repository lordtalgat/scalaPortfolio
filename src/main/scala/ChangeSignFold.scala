package org.talgat.shakirov

object ChangeSignFold {
  //The task is to count the number of sign changes in immutable Seq, 0 is not considered a sign change.
  // Loops (for, while, do) cannot be used in the task.
  // Example list(0, 1, -2) - 0+1-2 = -1 the sign changed once
  //life codding 10 minutes. After tail rec another method

  def exampleRun = {
    val in = List(1, 2, -4, 5, -7, 8, -10, 20, -16)

    val result = in.foldLeft((0, 0, true)) { case ((sum, chageSign, isGZ), b) =>
      isGZ match {
        case true => if (sum + b >= 0) {
          (sum + b, chageSign, isGZ)
        } else {
          (sum + b, chageSign + 1, false)
        }
        case _ => if (sum + b >= 0) {
          (sum + b, chageSign + 1, true)
        } else {
          (sum + b, chageSign, false)
        }
      }
    }
    println("List was = "+ in)
    printf("Sign changed %d times",result._2)
  }
}
