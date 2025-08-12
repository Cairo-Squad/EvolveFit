package com.cairosquad.evolvefit.domain.exceptions

open class EvolveException(message: String = "", cause: Throwable? = null) : Exception(message, cause)

class InternetConnectionException(message: String = "No internet connection", cause: Throwable? = null) :
    EvolveException(message, cause)

class TimeoutException(message: String = "Request timed out", cause: Throwable? = null) :
    EvolveException(message, cause)

class NetworkException(message: String = "Network error occurred", cause: Throwable? = null) :
    EvolveException(message, cause)

open class ApiException(
    val statusCode: Int,
    val errorBody: String? = null,
    message: String = "API error",
    cause: Throwable? = null
) : EvolveException(message, cause)

class UnauthorizedException(message: String = "Unauthorized", cause: Throwable? = null) :
    ApiException(401, message = message, cause = cause)

class ForbiddenException(message: String = "Forbidden", cause: Throwable? = null) :
    ApiException(403, message = message, cause = cause)

class NotFoundException(message: String = "Not Found", cause: Throwable? = null) :
    ApiException(404, message = message, cause = cause)

class ServerErrorException(
    statusCode: Int = 500,
    message: String = "Server error",
    cause: Throwable? = null
) : ApiException(statusCode, message = message, cause = cause)

class DomainEmptyResponseException(message: String = "Empty response", cause: Throwable? = null) :
    EvolveException(message, cause)

class DataParsingException(message: String = "Error parsing data", cause: Throwable? = null) :
    EvolveException(message, cause)

class UnknownException(message: String = "Unknown error", cause: Throwable? = null) :
    EvolveException(message, cause)