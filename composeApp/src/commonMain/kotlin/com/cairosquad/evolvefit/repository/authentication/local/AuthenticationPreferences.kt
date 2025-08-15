package com.cairosquad.evolvefit.repository.authentication.local

interface AuthenticationPreferences {
    fun saveTokens(access: String, refresh: String?)
    fun getAccessToken(): String?
    fun getRefreshToken(): String?
    fun clear()
}