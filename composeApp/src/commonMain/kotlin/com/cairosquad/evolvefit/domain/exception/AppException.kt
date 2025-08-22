package com.cairosquad.evolvefit.domain.exception

import com.cairosquad.evolvefit.domain.model.FieldType

sealed class AppException(message: String = "") : RuntimeException(message)

class NetworkException(message: String = "") : AppException(message)

class InternetConnectionException(message: String = "") : AppException(message)

class UnauthorizedUserException(message: String = "") : AppException(message)

class UnknownException(message: String = "") : AppException(message)

class InvalidEmailFormatException(message: String = "") : AppException(message)

class InvalidPasswordException(message: String = "") : AppException(message)

class TimeoutException(message: String = "") : AppException(message)

open class NutritionException(
    message: String = "",
) : AppException(message)

class ExceededCaloriesException(
    message: String = "",
) : NutritionException(message)

class ExceededWaterLimitException(
    message: String = "",
) : NutritionException(message)

class MealNotFoundException(
    message: String = "",
) : NutritionException(message)
open class FieldException(
    val field: FieldType
) : Exception()
class InvalidNumberFormatException(field: FieldType) : FieldException(field)
class NumberTooLargeException(field: FieldType) : FieldException(field)
class LengthTooLargeException(field: FieldType) : FieldException(field)