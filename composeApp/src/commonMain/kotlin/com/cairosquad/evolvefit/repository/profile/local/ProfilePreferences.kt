package com.cairosquad.evolvefit.repository.profile.local

import com.cairosquad.evolvefit.viewmodel.more.MoreScreenState

interface ProfilePreferences{
    fun saveTheme(theme: MoreScreenState.Theme)
    fun getSavedTheme(): MoreScreenState.Theme

    fun saveLanguage(language: String)
    fun getSavedLanguage(): String
}
