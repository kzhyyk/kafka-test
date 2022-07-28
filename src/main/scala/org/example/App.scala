package org.example

import sun.nio.cs.Surrogate.Generator

/**
 * Hello world!
 *
 */
object Main extends App {
  println( "Hello World!" )

//  回调函数
  def totalResultOverRange(number:Int, codeBlock:Int => Int)= {
    var res = 0
    for(i <- 1 to number){
      res += codeBlock(i)
    }
    res
  }
//  第二个参数是一个匿名的即时函数，左边是参数列表，右边是实现
  println(totalResultOverRange(10, i => i))
//  只计算偶数的和
  println(totalResultOverRange(10, i => if(i % 2 == 0) i else 0))
//  无参数的函数
  def printValue(generator: () => Int) : Unit= {
    println(s"value is ${generator()}")
  }
  printValue(() => 3)

  /**
   *
   * @param arr 数组
   * @param initial 初始值
   * @param operation 对数组中每个元素作为参数值调用
   * @return  最终值
   */
  def inject(arr: Array[Int], initial: Int, operation: (Int, Int) => Int) = {
    var carryover = initial
    arr.foreach(element => carryover = operation(carryover, element))
    carryover
  }

  val array = Array(2, 3, 5, 6, 1, 6)
  val sum = inject(array, 0, (carry, elem) => (carry + elem))
  println(s"Sum:$sum")

  val max = inject(array, Integer.MIN_VALUE, (carry, elem) => (Math.max(carry, elem)))
  println(s"Max:$max")
//  使用自带的foldleft
  val sum1 = array.foldLeft(0){(sum1, elem) => sum1 + elem}
//   更简洁的一种形式
  val sum2 = (0 /: array)((sum2, elem) => sum2 + elem)
  println(s"Sum2:$sum2")
  val max1 = array.foldLeft(Integer.MIN_VALUE){(large, elem) => Math.max(large, elem)}

//  柯里化。把接受多个参数的函数转化为接受多个参数列表的函数
//  重写inject方法，接受两个参数，第二个只接受函数值
  def inject2(arr: Array[Int], initial: Int)(operation: (Int, Int) => Int)  = {
    var carryover = initial
    arr.foreach(element => carryover = operation(carryover, element))
    carryover
  }
//  再次调用
  val sum3 = inject2(array, 0){(carryover, elem) => carryover + elem}

//  参数的占位符,减少函数值中的噪声
  val sum4 = (0 /: array){ _ + _ }
  val negativeNumber = array.exists{ _ < 0}
  val largest1 = (Integer.MIN_VALUE /: array){ Math.max _}
  val largest2 = (Integer.MIN_VALUE /: array){ Math.max }

//  复用函数值
  class Equipment(val routine: Int => Int) {
    def simulate(input: Int): Int = {
      print("Running simulation...")
      routine(input)
    }
  }

  def cal(input: Int) = {
    println(s"cal with $input")
    input
  }

  val equipment1 = new Equipment(cal)
  equipment1.simulate(3)

//  闭包
  //第二个参数是一个代码块，对于期间的每一个代码块，都会调用这个代码块，并传递给这个方法
  def loopThrough(number: Int)(closure: Int => Unit): Unit ={
    for (i <- 1 to number){ closure(i) }
  }
  var res = 0
  val addIt = {value: Int => res += value}
//  参数value绑定到了loop传来的参数上，res绑定到了loop的调用者所在上下文中的变量
  loopThrough(10){elem => addIt(elem)}
  println(s"Total of values from 1 to 10:$res")





}
