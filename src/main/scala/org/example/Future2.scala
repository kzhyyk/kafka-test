package org.example

import java.time.LocalTime

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.math.random
import scala.util.{Failure, Success}
object Future2 extends App {

  val res = Future{
    Thread.sleep(100)
    println(s"future:${LocalTime.now}")
    4
  }
  println(s"now:${LocalTime.now}")

//  通常使用回调函数，不使用阻塞等待
  res.onComplete{
    case Success(value) => println(s"future callback:$value")
    case Failure(exception) => println(exception.getMessage)
  }

//  组合future任务
  def getData1():Double = {
    Thread.sleep(100)
    val t = random()
    if (t < 0.5)
      println(s"t1!:$t")
    t
  }
  def getData2():Double = {
    Thread.sleep(100)
    val t = random()
    if (t > 0.5)
      println(s"t2!:$t")
    t
  }

  val f1 = Future{ getData1() }
  val f2 = Future{ getData2() } recover{ case e: Exception => 0 }
//  println(s"f1:$f1")
//  val combined = f1.flatMap(n1 => f2.map(n2 => n1 + n2))
//  println(s"combined:$combined")
  val combined = for (n1 <- f1 ; n2 <- f2) yield n1 + n2
  combined.onComplete{
    case Success(value) => println(s"combined callback:$value")
    case Failure(exception) => println(exception.getMessage)
  }
////  从整数至 Duration对象的转换方法
//  val res1 = Await.result(res, 200.millis)
//
//  Await.ready(res, 1000.millis)
////  value 方法返回的是一个Option[Try[T]].
////  future 没有完成时是None ，而在完成时是Some(t)
//  val Some(t) = res.value
//
//  t match {
//    case Success(value) => println(s"ans:$value")
//    case Failure(exception) => println(exception.getMessage)
//  }


    Thread.sleep(2000)
//  }
}
