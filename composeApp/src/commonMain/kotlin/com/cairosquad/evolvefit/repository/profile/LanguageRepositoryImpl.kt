package com.cairosquad.evolvefit.repository.profile

import com.cairosquad.evolvefit.domain.model.Language
import com.cairosquad.evolvefit.domain.repository.LanguageRepository
import com.cairosquad.evolvefit.repository.profile.local.LanguagePreferences

class LanguageRepositoryImpl(
    private val prefs: LanguagePreferences
) : LanguageRepository {

    override fun saveLanguage(language: Language) {
        prefs.saveLanguage(language)
    }

    override fun getLanguage(): Language {
        return prefs.getLanguage()
    }
}
