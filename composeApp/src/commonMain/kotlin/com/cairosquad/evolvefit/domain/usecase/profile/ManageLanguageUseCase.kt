package com.cairosquad.evolvefit.domain.usecase.profile

import com.cairosquad.evolvefit.domain.model.Language
import com.cairosquad.evolvefit.domain.repository.LanguageRepository

class ManageLanguageUseCase(
    private val languageRepository: LanguageRepository
) {
    fun saveLanguage(language: Language) {
        languageRepository.saveLanguage(language)
    }

    fun getLanguage(): Language {
        return languageRepository.getLanguage()
    }
}
