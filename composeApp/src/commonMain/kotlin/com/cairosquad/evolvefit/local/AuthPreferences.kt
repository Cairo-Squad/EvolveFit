package com.cairosquad.evolvefit.local

import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthPreferences(private val settings: Settings) {

    private val _accessTokenFlow = MutableStateFlow(getAccessToken())
    val accessTokenFlow: StateFlow<String?> = _accessTokenFlow

    fun saveTokens(access: String, refresh: String) {
        settings.putString("access_token", access)
        settings.putString("refresh_token", refresh)
        _accessTokenFlow.value = access
    }

    fun getAccessToken(): String? = settings.getStringOrNull("access_token")

    fun getRefreshToken(): String? = settings.getStringOrNull("refresh_token")

    fun clear() {
        settings.remove("access_token")
        settings.remove("refresh_token")
        _accessTokenFlow.value = null
    }
}
