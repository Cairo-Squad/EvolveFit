package com.cairosquad.evolvefit.remote

suspend inline fun <T> safeApiCall(apiCall: () -> T): T {
    try {
        return apiCall()
    } catch (e: Exception) {
        throw e
        //TODO we will map exceptions later
    }
}