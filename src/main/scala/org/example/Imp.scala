package org.example
/*隐式详解
*
* */
object Imp extends App {
//  rate为隐式参数
  def calcTax(amount: Float)(implicit rate: Float) = amount * rate

  implicit val currentTax = 0.03f
  val tax = calcTax(1000f)
  println("tax:" + tax)
}
