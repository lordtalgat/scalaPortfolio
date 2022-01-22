package org.talgat.shakirov

object Queens {
  //program to guide students from Big Data School of Alfa bank
  //Arrange the 8 queens on the chessboard so that they do not attack each other.
  // If you can write a program for the board n*n

  private def getQueens(n: Int): List[List[(Int, Int)]] = {

    def replaceQueens(k: Int): List[List[(Int, Int)]] = {
      if (k == 0) // if k=0 do nothing
        List(List())
      else
        for {
          queens <- replaceQueens(k - 1) // 1 generators
          column <- 1 to n // 2 generator
          queen = (k, column) //set position
          if isPosible(queen, queens) //cheking is posible to set this position
        } yield queen :: queens
    }

    replaceQueens(n)
  }

  private def isPosible(queen: (Int, Int), queens: List[(Int, Int)]) = {
    queens forall (q => !inCheck(queen, q)) // all postions must return false
  }

  private def inCheck(q1: (Int, Int), q2: (Int, Int)):Boolean = {
    q1._1 == q2._1 || // same row
      q1._2 == q2._2 || // same column
      (q1._1 - q2._1).abs == (q1._2 - q2._2).abs // same diagonal
  }

  def run = println(getQueens(8).mkString("\n"))
}
