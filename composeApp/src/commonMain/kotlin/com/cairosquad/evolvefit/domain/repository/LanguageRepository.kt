package com.cairosquad.evolvefit.domain.repository

import com.cairosquad.evolvefit.domain.model.Language

interface LanguageRepository {
    fun saveLanguage(language: Language)
    fun getLanguage(): Language
}
