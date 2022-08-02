package org.example


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
    sleep((math.random * 1100).toLong)
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
  sleep(1000)

}
