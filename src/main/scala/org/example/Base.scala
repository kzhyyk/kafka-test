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



  //最开始拥有的软妹币
  var money = 10
  //每天喝掉一个软妹币
  def drink: Unit = {
    money -= 1
  }
  //数钱时要算上被喝掉的软妹币
  def count: Int = {
    drink
    money
  }
  //每天都数钱
  def printByName(x: => Int): Unit = {
    for(i <- 0 until 5)
      println("每天算一算，酒鬼还剩" + x + "块钱！")
  }
  //第一天数一下记墙上，以后每天看墙上的余额
  def printByValue(x: Int): Unit = {
    for(i <- 0 until 5)
      println("只算第一天，酒鬼还剩" + x + "块钱！")
  }

  printByName(count)
  printByValue(count)



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
