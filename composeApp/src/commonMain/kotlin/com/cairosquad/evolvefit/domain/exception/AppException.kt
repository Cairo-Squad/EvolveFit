package com.cairosquad.evolvefit.domain.exception

sealed class AppException(message: String = "") : RuntimeException(message)

class NetworkException(message: String = "") : AppException(message)

class InternetConnectionException(message: String = "") : AppException(message)

class UnauthorizedUserException(message: String = "") : AppException(message)

class UnknownException(message: String = "") : AppException(message)