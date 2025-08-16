package com.cairosquad.evolvefit.repository.execption

import com.cairosquad.evolvefit.domain.exception.AppException
import com.cairosquad.evolvefit.domain.exception.InternetConnectionException
import com.cairosquad.evolvefit.domain.exception.NetworkException
import com.cairosquad.evolvefit.domain.exception.UnauthorizedUserException
import com.cairosquad.evolvefit.domain.exception.UnknownException
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.io.IOException
import kotlinx.serialization.SerializationException

suspend fun <T> callDataSource(execute: suspend () -> T): T {
    return try {
        execute()
    } catch (e: RepositoryException) {
        throw getDomainExceptionFromRepositoryException(e)
    } catch (e: IOException) {
        throw InternetConnectionException(e.message ?: "")
    } catch (e: okio.IOException) {
        throw InternetConnectionException(e.message ?: "")
    } catch (e: UnresolvedAddressException) {
        throw InternetConnectionException(e.message ?: "")
    } catch (e: SerializationException) {
        throw NetworkException(e.message ?: "")
    } catch (e: Exception) {
        throw UnknownException(e.message ?: "")
    }
}

fun getDomainExceptionFromRepositoryException(exception: RepositoryException): AppException {
    return when (exception) {
        is ApiException -> when (exception) {
            is RequestTimeoutException -> InternetConnectionException(exception.message)
            is TooManyRequestsException -> NetworkException(exception.message)
            is ForbiddenException -> NetworkException(exception.message)
            is ConflictException -> NetworkException(exception.message)
            is BadRequestException -> NetworkException(exception.message)
            is NotFoundException -> UnknownException(exception.message)
            is UnauthorizedException -> UnauthorizedUserException(exception.message)
        }

        is ServerException -> NetworkException(exception.message)
        is NoInternetException -> InternetConnectionException(exception.message)
        is UnknownDataSourceException -> UnknownException(exception.message)
        is RepoJsonParsingException -> NetworkException(exception.message)
    }
}