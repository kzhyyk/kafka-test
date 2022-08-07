package org.example


import java.time.LocalTime

import scala.annotation.tailrec
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}


object FutureIn extends App {

  def sleep(millis: Long) = {
//    模拟处理工作
    Thread.sleep(millis)
  }
  def dowork(index: Int) = {
//    随机一下
    sleep((math.random * 100).toLong)
    index
  }

//  并发五个任务并在任务结束时返回结果
  (1 to 5) foreach { index =>
    val f = Future{
//      传入一个匿名函数
      dowork(index)
    }
//    注册回调函数
    f onComplete{
      case Success(index: Int) => println(s"success!->$index")
      case Failure(th) => println(s"failure!->$th")
    }
  }
//  等待所有任务执行完
  sleep(8000)

  Future{
    Thread.sleep(1000)
    println(s"future:${LocalTime.now}")
  }
  println(s"now:${LocalTime.now}")




//  偏函数
//  偏函数被包在花括号内没有match的一组case语句是一个偏函数
//  偏函数是PartialFunction[A, B]的一个实例
//  A代表输入参数类型,B代表返回结果类型
  val function0: PartialFunction[Int, String] = {
    case 1 => "一"
    case 2 => "二"
    case 3 => "三"
    case _ => "其它"
  }
  println(function0(3))
  println(function0(10))

  val list = (0 to 10).toList
  val list2 = list.map {
    case x if x >= 1 && x <= 3 => "[1-3]"
    case x if x >= 4 && x <= 8 => "[4-8]"
    case x if x > 8 => "(8-*]"
    case _ => "其它"
  }
  println(list2)

//  递归
  println(factorial(10))

  def factorial(index:Int):Long = {
    @tailrec
    def fact(index: Int, acc: Int): Long={
      if (index < 1) acc
      else fact(index - 1, index * acc)
    }
    fact(index, 1)
  }
}
