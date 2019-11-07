package example

object A {
  val a = "a"
  val b = Some(Right(List(1)))
  val c = None
  val d = Some(Left("left"))
  val e = Some(Right(List(2)))

  val some = List(b,c)
  val someOthers = List(d)
  val all = some ++ someOthers
}

case class A()
