package com.cairosquad.evolvefit.local

import com.russhwolf.settings.Settings

class AuthPreferences(private val settings: Settings) {
    fun saveTokens(access: String, refresh: String) {
        settings.putString("access_token", access)
        settings.putString("refresh_token", refresh)
    }

//    fun saveAccessToken(token: String) {
//        settings.putString("access_token", token)
//    }
//make save individually
    fun getAccessToken(): String? = settings.getStringOrNull("access_token")

    fun getRefreshToken(): String? = settings.getStringOrNull("refresh_token")

    fun clear() {
        settings.remove("access_token")
        settings.remove("refresh_token")
    }
}