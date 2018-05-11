package validation

import org.scalatest.{FlatSpec, Matchers}

class FailFastValidationSpec extends FlatSpec with Matchers {

  "validate" should "generate a User for valid input data" in {
    val validatedUser: Either[ValidationError, User] =
      FailFastValidation.validate(name = "Rama",
                                  age = "18",
                                  address = "23 Priory Lane, London, SE1 3RG",
                                  hair = "luscious locks")

    validatedUser shouldBe Right(
      User(name = "Rama", age = 18, address = "23 Priory Lane, London, SE1 3RG", hair = "luscious locks"))

  }

  it should "return an error for failed validation" in {
    val validatedUser =
      FailFastValidation.validate(name = "Rama",
                                  age = "-18",
                                  address = "23 Priory Lane, London, SE1 3RG",
                                  hair = "luscious locks")

    validatedUser shouldBe Left(ValidationError("age '-18' was not a positive number"))
  }

  it should "fail fast and only return the first error" in {
    val validatedUser =
      FailFastValidation.validate(name = "$%^",
                                  age = "-18",
                                  address = "23 Priory Lane, London, SE1 3RG",
                                  hair = "luscious locks")

    validatedUser shouldBe Left(ValidationError("name '$%^' has invalid characters"))
  }
}
