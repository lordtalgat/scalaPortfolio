package org.talgat.shakirov

object ZipLists {
  //1) Zip sequence of int Seq(1, 2, 2, 3, 4, 3, 3, 3) => Seq((1, 1), (2, 2), (3, 1), (4, 1), (3, 3))
  // Answer must be like Seq[(Int, Int)] (Int from seq and count of this appearance)
  //2) Unzip sequence
  //Life coding took 16 minutes
  val in = Seq(1, 2, 2, 3, 4, 3, 3, 3)

  val ziped = in.foldLeft(List.empty[(Int, Int)]) {
    case (List(), b) => List((b, 1))
    case (h :: tl, b) =>
      if (h._1 == b) {
        (b, h._2 + 1) :: tl
      } else {
        (b, 1) :: h :: tl
      }
  }.reverse

  val unziped = ziped.flatMap { case(e, c) =>
    Seq.fill(c)(e)
  }


def run() = {
  println("Ziped will like")
  println(ziped)
  println("----------------")
  println("UnZiped will like")
  println(unziped)
}
}
