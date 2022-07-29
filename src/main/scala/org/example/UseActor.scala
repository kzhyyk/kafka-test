package org.example
import akka.actor._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object UseActor extends App {
  val system = ActorSystem("sample")
  val depp = system.actorOf(Props[HollywoodActor])

  depp ! "Wonda"

  val terminate = system.terminate()
  Await.ready(terminate, Duration.Inf)
}

class HollywoodActor() extends Actor{
  def receive: Receive = {
    case message => println(s"playing the role of $message")
  }
}
