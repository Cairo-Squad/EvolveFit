package com.cairosquad.evolvefit.repository.execption

sealed class RepositoryException(override val message: String = "") : RuntimeException(message)

// General errors
class UnknownDataSourceException(override val message: String = "") : RepositoryException(message)
class ServerException(override val message: String = "") : RepositoryException(message)
class NoInternetException(override val message: String = "") : RepositoryException(message)

// repo
class RepoJsonParsingException(override val message: String = "") : RepositoryException(message)

// API-specific
sealed class ApiException(override val message: String = "") : RepositoryException(message)
class RequestTimeoutException(override val message: String = "") : ApiException(message)
class TooManyRequestsException(override val message: String = "") : ApiException(message)
class UnauthorizedException(override val message: String = "") : ApiException(message)
class ForbiddenException(override val message: String = "") : ApiException(message)
class NotFoundException(override val message: String = "") : ApiException(message)
class ConflictException(override val message: String = "") : ApiException(message)
class BadRequestException(override val message: String = "") : ApiException(message)
