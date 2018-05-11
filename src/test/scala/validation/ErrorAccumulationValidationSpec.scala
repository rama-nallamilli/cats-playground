package validation

import cats.data.NonEmptyList
import cats.data.Validated.{Invalid, Valid}
import org.scalatest.{FlatSpec, Matchers}
import validation.ErrorAccumulationValidation.ValidationResult

class ErrorAccumulationValidationSpec extends FlatSpec with Matchers {

  "validate" should "generate a User for valid input data" in {
    val validatedUser: ValidationResult[User] =
      ErrorAccumulationValidation.validate(name = "Rama",
                                           age = "18",
                                           address = "23 Priory Lane, London, SE1 3RG",
                                           hair = "luscious locks")

    validatedUser shouldBe Valid(
      User(name = "Rama", age = 18, address = "23 Priory Lane, London, SE1 3RG", hair = "luscious locks"))

  }

  it should "return multiple errors for failed validations" in {
    val validatedUser =
      ErrorAccumulationValidation.validate(name = "%$#",
                                           age = "-18",
                                           address = "23 Priory Lane, London, SE1 3RG",
                                           hair = "luscious locks")

    val errors = NonEmptyList(head = ValidationError("name '%$#' has invalid characters"),
                              tail = List(ValidationError("age '-18' was not a positive number")))

    validatedUser shouldBe Invalid(errors)
  }
}
