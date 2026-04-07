package com.cairosquad.evolvefit.repository.utils

import kotlin.concurrent.Volatile

class BaseUrlProvider {
    @Volatile
    var baseUrl: String = ""
}