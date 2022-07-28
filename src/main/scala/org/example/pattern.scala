package org.example

object pattern extends App {
  //  匹配字面量和枚举值
  def activity(day: String): Unit = {
    day match {
      case "Sunday" => print("Eat, sleep, repeat... ")
      case "Saturday" => print("Hang out with friends... ")
      case "Monday" => print("...code for fun...")
      case "Friday" => print("...read a good book...")
      //        下划线表示通配符
      case _ => println("for fun")
    }
  }

  //  对日期进行匹配
  List("Monday", "Sunday", "Saturday", "Thursday").foreach {
    activity
  }

  //  匹配元组和列表
  def processCoordinates(input: Any): Unit = {
    input match {
      case (lat, long) => printf("Processing (%d, %d)...", lat, long)
      case "done" => println("done")
      case _ => println("invalid input")
    }
  }

  processCoordinates((39, -104))
  processCoordinates("done")
  //  processCoordinates((39, -104.1))

  //  对List进行匹配
  def processItems(items: List[String]): Unit = {
    items match {
      case List("apple", "ibm") => println("Apples and IBMs")
      case List("red", "blue", "white") => println("Stars and Stripes...")
//        使用数组展开_*
      case List("red", "blue", _*) => println("colors red, blue, ... ")
//        使用数组展开，并放置一个新的变量名otherFruits@_*
      case List("apple", "orange", otherFruits@_*) =>
        println("apples, oranges, and " + otherFruits)
    }
  }

  processItems(List("apple", "ibm"))
  processItems(List("red", "blue", "green"))
  processItems(List("red", "blue", "white"))
  processItems(List("apple", "orange", "grapes", "dates"))




}
