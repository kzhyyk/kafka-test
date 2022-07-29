package org.example

object LazyEvaluation extends App {
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


//  终极惰性流

//  创建一个数值生成器，每拉取一次，都会产生一个新的数值，接着再产生序列中的下一个。
  def generate(start: Int): LazyList[Int] = {
    start #:: generate(start + 1)
  }
//  如果不在该流上进行任何的调用，那么它将不会进行任何实际的工作，也不会为元素占用任何的空间
//  只有一种方法可以使流生成值并执行一些操作：
//  必须从中强制得到一个结果
  println(generate(25))
//  take方法有助于将流中的元素个数限定为有限个
  println(generate(25).take(10).force)
//  使用takewhile方法，接受一个函数值作为参数，只要函数值中表达式为true，一直持续生成值
  println(generate(25).takeWhile(_ < 35).force)


//  并行集合


}
