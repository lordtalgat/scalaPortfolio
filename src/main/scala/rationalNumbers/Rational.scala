package org.talgat.shakirov
package rationalNumbers

class Rational(n: Int, d: Int) extends Ordered[Rational]{
  require(d != 0)

  private val g = gcd(n.abs, d.abs)
  val numer: Int = n / g
  val denom: Int = d / g

  def lessThan(that: Rational): Boolean = {
    this.numer * that.denom < that.numer * this.denom
  }

  def max(that: Rational): Rational = if (lessThan(that)) that else this

  def +(that: Rational): Rational = {
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )
  }

  def +(i: Int): Rational = {
    new Rational(numer + i * denom, denom)
  }

  def -(that: Rational): Rational = {
    new Rational(
      numer * that.denom - that.numer * denom, denom * that.denom
    )
  }

  def -(i: Int): Rational = {
    new Rational(numer - i * denom, denom)
  }

  def *(that: Rational): Rational = {
    new Rational(numer * that.numer, denom *
      that.denom)
  }

  def *(i: Int): Rational ={
    new Rational(numer * i, denom)
  }

  def /(that:Rational):Rational = {
    new Rational(numer * that.denom, denom *
      that.numer)
  }

  def /(i:Int): Rational = {
    new Rational(numer, denom * i)
  }

  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)

  override def toString: String = numer + "/" + denom

  override def compare(that: Rational): Int = { //<0 if less; 0 equals; 0> if greater
    (this.numer * that.denom) - (that.numer * this.denom)
  }
}

object Rational { //Object companion or Sputnik
  def apply(n: Int, d: Int): Rational = new Rational(n, d)
  def apply(n: Int): Rational = new Rational(n, 1)
  implicit def intToRational(x:Int) = new Rational(x, 1)
}