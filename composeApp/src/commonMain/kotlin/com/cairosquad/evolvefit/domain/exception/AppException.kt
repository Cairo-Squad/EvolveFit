package com.cairosquad.evolvefit.domain.exception

sealed class AppException(message: String = "") : RuntimeException(message)

class NetworkException(message: String = "") : AppException(message)

class InternetConnectionException(message: String = "") : AppException(message)

class UnauthorizedUserException(message: String = "") : AppException(message)

class UnknownException(message: String = "") : AppException(message)

class InvalidEmailFormatException(message: String = "") : AppException(message)

class InvalidPasswordException(message: String = "") : AppException(message)

class TimeoutException(message: String = "") : AppException(message)

open class NutritionException(
    message: String = "Nutrition error",
) : AppException(message)

class ExceededCaloriesException(
    message: String = "Not enough remaining calories",
) : NutritionException(message)

class ExceededWaterLimitException(
    message: String = "Not enough remaining water allowance",
) : NutritionException(message)

class MealNotFoundException(
    message: String = "Meal not found",
) : NutritionException(message)

class InvalidNumberFormatException(
    message: String = "Only numbers and decimal point are allowed",
) : AppException(message)