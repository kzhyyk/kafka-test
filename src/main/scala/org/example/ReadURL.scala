package org.example

import java.net.URL
import scala.io.Source
object ReadURL extends App {
  val source = Source.fromURL(new URL("http://www.jrkblog.top/"))
  for (line <- source.getLines()) {
    println(line)
  }

//  val response: HttpResponse[String] = Http("http://www.jrkblog.top/").asString
//  println(response.body)
//  response.code
//  response.headers
//  response.cookies
//  val body = response.body
//  println(source.mkString.getClass)


}
