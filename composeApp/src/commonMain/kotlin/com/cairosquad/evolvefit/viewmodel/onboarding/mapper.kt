package com.cairosquad.evolvefit.viewmodel.onboarding

import com.cairosquad.evolvefit.domain.model.Language

fun OnboardingScreenState.Language.toDomain(): Language {
    return when (this) {
        OnboardingScreenState.Language.ENGLISH -> Language.ENGLISH
        OnboardingScreenState.Language.ARABIC -> Language.ARABIC

    }
}

fun languageToLanguageCode(language: Language): String {
    return when (language) {
        Language.ARABIC -> "ar"
        Language.ENGLISH -> "en"
    }
}