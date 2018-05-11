package monoid

import cats.syntax.semigroup._
import org.scalatest.{FlatSpec, Matchers}

class Vector2Spec extends FlatSpec with Matchers {

  "Vector2" should "be combined" in {

    Vector2(3, 4) |+| Vector2(10, 3) |+| Vector2(2, 4) shouldBe Vector2(15, 11)
    Vector2(-3, 4) |+| Vector2(3, 3) shouldBe Vector2(0, 7)
    Vector2(-3, 4) |+| Vector2(-3, 3) shouldBe Vector2(-6, 7)
    Vector2(1, 1) |+| Vector2(2, 2) shouldBe Vector2(3, 3)

  }

}
