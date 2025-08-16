package com.cairosquad.evolvefit.repository.profile

import com.cairosquad.evolvefit.domain.model.Language
import com.cairosquad.evolvefit.domain.repository.LanguageRepository
import com.cairosquad.evolvefit.repository.profile.local.LanguagePreferences

class LanguageRepositoryImpl(
    private val languagePreferences: LanguagePreferences
) : LanguageRepository {

    override fun saveLanguage(language: Language) {
        languagePreferences.saveLanguage(language)
    }

    override fun getLanguage(): Language {
        return languagePreferences.getLanguage()
    }
}
