package com.cairosquad.evolvefit

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform