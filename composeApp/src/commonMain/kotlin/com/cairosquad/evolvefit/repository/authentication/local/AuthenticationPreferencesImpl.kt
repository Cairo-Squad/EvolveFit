package com.cairosquad.evolvefit.repository.authentication.local

import com.russhwolf.settings.Settings

class AuthenticationPreferencesImpl(private val settings: Settings) : AuthenticationPreferences {

    override fun saveTokens(access: String, refresh: String?) {
        settings.putString(ACCESS_KEY, access)
        settings.putString(REFRESH_KEY, refresh.orEmpty())
    }

    override fun getAccessToken(): String? = settings.getStringOrNull(ACCESS_KEY)

    override fun getRefreshToken(): String? = settings.getStringOrNull(REFRESH_KEY)

    override fun clear() {
        settings.remove(ACCESS_KEY)
        settings.remove(REFRESH_KEY)
    }

    private companion object {
        const val ACCESS_KEY = "access_token"
        const val REFRESH_KEY = "refresh_token"
    }
}