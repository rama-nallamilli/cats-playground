package validation

import scala.util.{Failure, Success, Try}

object FailFastValidation {

  private def validateName(name: String): Either[ValidationError, String] =
    if (name.matches("^[a-zA-Z0-9]+$"))
      Right(name)
    else
      Left(ValidationError(s"name '$name' has invalid characters"))

  private def validateAge(age: String): Either[ValidationError, Int] = {
    Try(age.toInt) match {
      case Success(n) if n < 0 =>
        Left(ValidationError(s"age '$age' was not a positive number"))
      case Success(n) => Right(n)
      case Failure(_) =>
        Left(ValidationError(s"age '$age' could not be parsed"))
    }
  }

  private def validateAddress(address: String): Either[ValidationError, String] = Right(address)

  private def validateHair(hair: String): Either[ValidationError, String] = Right(hair)

  def validate(name: String, age: String, address: String, hair: String): Either[ValidationError, User] = {

    for {
      validName    <- validateName(name)
      validAge     <- validateAge(age)
      validAddress <- validateAddress(address)
      validHair    <- validateHair(hair)
    } yield User(validName, validAge, validAddress, validHair)

  }
}
