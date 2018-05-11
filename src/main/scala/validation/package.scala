package object validation {

  case class ValidationError(reason: String)

  case class User(name: String, age: Int, address: String, hair: String)

}
