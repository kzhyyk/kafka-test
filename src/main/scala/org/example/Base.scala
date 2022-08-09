package org.example

import java.io.File

import scala.language.postfixOps

object Base extends App {
  1 toString

//  定义无副作用的无参方法时省略括号
//  List(1, 2, 4, 5, 6) filter isEven foreach println
  val l1 = List(1, 2, 4, 5, 6)
  val l2 = LazyList("a", "b a", "c a", "d", "e", "f", "g")
  8::l1 filter isEven foreach println
  def isEven(n: Int)  = (n % 2) == 0

//  for循环,生成器表达式
  for (x <- l1)
    print(s"$x")
  println()

//  为for循环添加保护式
  for (x <- l2
       if x.contains("a")
       if !x.startsWith("c"))
    println(s"l2:$x")


//  传值与传名调用
  def time() = {
    println("获取时间")
    System.nanoTime
  }
//  传名调用，再函数内部计算。可以认为传入了一个表达式，在调用时才会执行
  def delayed(t: => Long) = {
    println("delayed:")
    println(t)
  }
//  传值调用，先计算再应用。提高效率。()=>Long一样
  def byvalue(t:Long) = {
    println("byvalue")
    println(t)
  }
  delayed(time())
  byvalue(time())

//  匿名函数
  val value = List(1, 2, 3)
  val doublevalue = value.map(_ * 2)
  //_ * 2就是一个匿名函数，_表示传递给此函数的参数
  println(s"doublevalue:$doublevalue")

  val ji = (x: Int, y: Int) => x * y
  println("积：" + ji(2, 3))




//  闭包
//  (y: Int) => x - y 这是一个匿名函数，使用了函数外的x
  def minusxy(x: Int) = (y: Int) => x - y
  //f函数就是闭包.
  val f = minusxy(20)
  println("f(1)=" + f(1)) // 19
  println("f(2)=" + f(2)) // 18




  //  新建txt文件
//  scala不支持三元表达式
  val configFile = new File("test1.txt")
  val configFilePath = if (configFile.exists){
    configFile.getAbsolutePath
  }else{
    configFile.createNewFile
    configFile.getAbsolutePath
  }
  println(configFilePath)

}
