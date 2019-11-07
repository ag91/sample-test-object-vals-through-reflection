package example

import org.scalatest._

class HelloSpec extends FlatSpec with Matchers {
  "The A object" should "contain some vals" in {
    A.a shouldEqual "a"
    A.b shouldEqual Some(Right(List(1)))
    A.c shouldEqual None
    A.d shouldEqual Some(Left("left"))
    A.e shouldEqual Some(Right(List(2)))
  }

  it should "contain a list with some values" in {
    A.some shouldEqual List(A.b,A.c)
    A.someOthers shouldEqual List(A.d)
  }

  it should "contain an 'all' list containing all vals in the object with the list type" in {
    Utils.containsAllValsInObject(A.all, A) shouldBe true
  }
}
