package validation

import cats.data.Validated._
import cats.data.ValidatedNel
import cats.implicits._

import scala.util.{Failure, Success, Try}

object ErrorAccumulationValidation {

  type ValidationResult[A] = ValidatedNel[ValidationError, A]

  def validate(name: String,
               age: String,
               address: String,
               hair: String): ValidationResult[User] = {
    (validateName(name),
      validateAge(age),
      validateAddress(address),
      validateHair(hair)).mapN(User)

  }

  private def validateName(name: String): ValidationResult[String] =
    if (name.matches("^[a-zA-Z0-9]+$"))
      name.validNel
    else
      ValidationError(s"name '$name' has invalid characters").invalidNel

  private def validateAge(age: String): ValidationResult[Int] = {
    Try(age.toInt) match {
      case Success(n) if n < 0 =>
        ValidationError(s"age '$age' was not a positive number").invalidNel
      case Success(n) => n.validNel
      case Failure(_) =>
        ValidationError(s"age '$age' could not be parsed").invalidNel
    }
  }

  private def validateAddress(address: String): ValidationResult[String] =
    address.validNel

  private def validateHair(hair: String): ValidationResult[String] =
    hair.validNel

}
