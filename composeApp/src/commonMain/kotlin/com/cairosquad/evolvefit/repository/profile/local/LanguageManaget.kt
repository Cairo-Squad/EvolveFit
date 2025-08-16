package com.cairosquad.evolvefit.repository.profile.local

import com.cairosquad.evolvefit.domain.model.Language

interface LanguagePreferences {
    fun saveLanguage(language: Language)
    fun getLanguage(): Language
}
