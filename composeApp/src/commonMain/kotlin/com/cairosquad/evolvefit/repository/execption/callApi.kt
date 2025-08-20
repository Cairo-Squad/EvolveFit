package com.cairosquad.evolvefit.repository.execption

import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.io.IOException


suspend inline fun <reified T> callApi(
    crossinline execute: suspend () -> HttpResponse
): T {
    val response = try {
        val res = execute()
        println("RAW RESPONSE BODY: ${res.bodyAsText()}")
        res
    } catch (e: IOException) {
        throw NoInternetException(e.message ?: "")
    }

    return responseToException(response)
}


suspend inline fun <reified T> responseToException(
    response: HttpResponse
): T {
    when (response.status.value) {
        in 200..299 -> {
            try {
                return response.body<T>()
            } catch (e: NoTransformationFoundException) {
                throw e
            }
        }
        401 -> throw UnauthorizedException(response.body<ErrorDto>().message ?: "")
        408 -> throw RequestTimeoutException(response.body<ErrorDto>().message ?: "")
        429 -> throw TooManyRequestsException(response.body<ErrorDto>().message ?: "")
        in 500..599 -> throw ServerException(response.body<ErrorDto>().message ?: "")
        else -> throw UnknownDataSourceException(response.body<ErrorDto>().message ?: "")
    }
}
