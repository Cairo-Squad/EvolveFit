package com.cairosquad.evolvefit.domain.usecase.profile

import com.cairosquad.evolvefit.repository.profile.local.ProfilePreferences
import com.cairosquad.evolvefit.viewmodel.more.MoreScreenState

class ManagePreferencesUseCase (
    private val preferences: ProfilePreferences
){
     fun saveTheme(theme: MoreScreenState.Theme) = preferences.saveTheme(theme)
     fun getTheme() = preferences.getSavedTheme()
     fun saveLanguage(language: String) = preferences.saveLanguage(language)
     fun getLanguage() = preferences.getSavedLanguage()
}