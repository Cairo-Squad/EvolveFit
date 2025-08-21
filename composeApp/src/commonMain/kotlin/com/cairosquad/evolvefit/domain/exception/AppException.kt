package com.cairosquad.evolvefit.domain.exception

sealed class AppException(message: String = "") : RuntimeException(message)

class NetworkException(message: String = "") : AppException(message)

class InternetConnectionException(message: String = "") : AppException(message)

class UnauthorizedUserException(message: String = "") : AppException(message)

class UnknownException(message: String = "") : AppException(message)

class InvalidEmailFormatException(message: String = "") : AppException(message)

class InvalidPasswordException(message: String = "") : AppException(message)

open class EvolveException(message: String = "", cause: Throwable? = null) : Exception(message, cause)

class TimeoutException(message: String = "Request timed out", cause: Throwable? = null) :
    EvolveException(message, cause)

open class NutritionException(
    message: String = "Nutrition error",
    cause: Throwable? = null
) : EvolveException(message, cause)

class ExceededCaloriesException(
    message: String = "Not enough remaining calories",
    cause: Throwable? = null
) : NutritionException(message, cause)

class ExceededWaterLimitException(
    message: String = "Not enough remaining water allowance",
    cause: Throwable? = null
) : NutritionException(message, cause)

class MealNotFoundException(
    message: String = "Meal not found",
    cause: Throwable? = null
) : NutritionException(message, cause)

class InvalidNumberFormatException(
    message: String = "Only numbers and decimal point are allowed",
    cause: Throwable? = null
) : EvolveException(message, cause)