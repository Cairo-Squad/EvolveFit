package com.cairosquad.evolvefit.repository.authentication.local

import com.russhwolf.settings.Settings

class AuthenticationPreferences(private val settings: Settings) {

    fun saveTokens(access: String, refresh: String) {
        settings.putString(ACCESS_KEY, access)
        settings.putString(REFRESH_KEY, refresh)
    }

    fun getAccessToken(): String? = settings.getStringOrNull(ACCESS_KEY)

    fun getRefreshToken(): String? = settings.getStringOrNull(REFRESH_KEY)

    fun clear() {
        settings.remove(ACCESS_KEY)
        settings.remove(REFRESH_KEY)
    }

    private companion object {
        const val ACCESS_KEY = "access_token"
        const val REFRESH_KEY = "refresh_token"
    }
}