package com.cairosquad.evolvefit.domain.usecase.profile

import com.cairosquad.evolvefit.domain.model.Language
import com.cairosquad.evolvefit.domain.repository.LanguageRepository

class ManageLanguageUseCase(
    private val languageRepository: LanguageRepository
) {
    fun save(language: Language) {
        languageRepository.saveLanguage(language)
    }

    fun get(): Language {
        return languageRepository.getLanguage()
    }
}
