package com.cairosquad.evolvefit.domain.usecase.profile

import com.cairosquad.evolvefit.domain.model.Language
import com.cairosquad.evolvefit.domain.repository.LanguageRepository

class ManageLanguageUseCase(
    private val repo: LanguageRepository
) {
    fun save(language: Language) {
        repo.saveLanguage(language)
    }

    fun get(): Language {
        return repo.getLanguage()
    }
}
