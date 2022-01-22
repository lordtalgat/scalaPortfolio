package org.talgat.shakirov
package rationalNumbers

object RationalExample {
  //program to guide students from Big Data School of Alfa bank
  //How to run +, -, *, / rational numbers (1/2)

  def run = {
    val oneHalf = new Rational(1, 2)
    println(oneHalf)
    val twoThirds = new Rational(2, 3)
    println(twoThirds)
    println("sum of rational")
    println(oneHalf + twoThirds)

    println("multiply of rational")
    println(twoThirds * twoThirds)
    val y = Rational(5) // rational from int
    println(twoThirds * y)

    println(2 * twoThirds) // working for to ways implicits
    // compare
    println(Rational(1, 2) > Rational(2, 3))
    val list = Seq(Rational(1, 2), Rational(2, 3), Rational(1), Rational(5, 2), Rational(7, 2))
    println(list.sortWith(_ > _))
  }
}