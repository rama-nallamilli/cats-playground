# cats-playground

This project includes working example use cases (including tests) for:

* Error accumulated validation using cats `Validated[NonEmptyList[E], A]`
  * [See usage](src/main/scala/validation/ErrorAccumulationValidation.scala) and [tests](src/test/scala/validation/ErrorAccumulationValidationSpec.scala)
* Fail fast validation using scala `Either[+A, +B]`
  * [See usage](src/main/scala/validation/FailFastValidation.scala) and [tests](src/test/scala/validation/FailFastValidationSpec.scala)
* Monoid combine for Vector implementation
  * [See usage](src/main/scala/monoid/Vector2.scala) and [tests](src/test/scala/monoid/Vector2Spec.scala)
* More to follow...
