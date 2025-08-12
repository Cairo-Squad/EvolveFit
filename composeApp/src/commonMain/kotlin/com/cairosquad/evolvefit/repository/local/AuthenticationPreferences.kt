package com.cairosquad.evolvefit.repository.local

import com.russhwolf.settings.Settings

class AuthenticationPreferences(private val settings: Settings) {

    fun saveTokens(access: String, refresh: String) {
        settings.putString("access_token", access)
        settings.putString("refresh_token", refresh)
    }

    fun getAccessToken(): String? = settings.getStringOrNull("access_token")

    fun getRefreshToken(): String? = settings.getStringOrNull("refresh_token")

    fun clear() {
        settings.remove("access_token")
        settings.remove("refresh_token")
    }
}
