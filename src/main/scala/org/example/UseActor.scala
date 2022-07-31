package org.example
import akka.actor._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object UseActor extends App {
//  akka的Actor托管在一个ActorSystem中，管理了线程，消息队列以及生命周期
  val system = ActorSystem("sample")
//  使用actorof工厂方法来创建
  val depp = system.actorOf(Props[HollywoodActor])
  val hanks = system.actorOf(Props[HollywoodActor])
//  发送消息给
  depp ! "A"
  hanks ! "B"

  depp ! "C"
  hanks ! "D"

//  退出线程
  val terminate = system.terminate()
  Await.ready(terminate, Duration.Inf)
}

//继承Actor特质并实现receive方法
//recsive是去掉match关键字的模式匹配语法
class HollywoodActor() extends Actor{
  def receive: Receive = {
    case message => println(s"playing the role of $message")
  }
}
