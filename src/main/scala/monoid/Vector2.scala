package monoid

import cats.Monoid
import Vector2.ZeroVector

case class Vector2(x: Double, y: Double) {

  def *(i: Double) = Vector2(x = x * i, y = y * i)

  def normalized: Vector2 = {
    val mag = magnitude
    if (mag > 0.0)
      Vector2(x = x / mag, y = y / mag)
    else
      ZeroVector
  }

  def magnitude: Double = {
    Math.sqrt(x * x + y * y)
  }
}

object Vector2 {

  val ZeroVector = Vector2(x = 0.0, y = 0.0)

  implicit val vectorMonoid = new Monoid[Vector2] {
    override def empty: Vector2 = ZeroVector

    override def combine(a: Vector2, b: Vector2): Vector2 = Vector2(x = a.x + b.x, y = a.y + b.y)
  }
}
