package org.example

object lazy_bingxing extends App {
  def expensiveComputation() = {
    println("hello!")
    false
  }

  lazy val perform = expensiveComputation()
  def eva(input: Int) = {
    println("value :" + input)
    if (input >= 10 && perform)

      println("doing!: " )
    else
      println("skipping")
  }

//  惰性求值
  eva(0)
  eva(100)

//  释放严格集合的惰性

  val people = List(("Mark", 32), ("Bob", 22), ("Jane", 8), ("Jill", 21),
    ("Nick", 50), ("Nancy", 42), ("Mike", 19), ("Sara", 12), ("Paula", 42),
    ("John", 21))

  def isOlderThan17(person: (String, Int)) = {
      println(s"isOlderThan17 called for $person")
      val (_, age) = person
      age > 17
    }
  def isNameStartsWithJ(person: (String, Int)) = {
      println(s"isNameStartsWithJ called for $person")
      val (name, _) = person
      name.startsWith("J")
    }

//  这种方式工作量大
//  println(people.filter { isOlderThan17 }.filter { isNameStartsWithJ }.head)
//  使用view视图来保持惰性。head方法最终触发实际的执行。
//  惰性求值比严格求值更加高效
  println(people.view.filter{ isOlderThan17}. filter{ isNameStartsWithJ}.head )


}
