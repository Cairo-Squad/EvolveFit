package com.cairosquad.evolvefit.ui.screen.onBoarding

data class OnboardingScreenState(
    val selectedLanguage: Language = Language.ENGLISH,
    val isBottomSheetOpen: Boolean = false,
    val bottomSheetSelectedLanguage : Language = Language.ENGLISH
) {
    enum class Language( val displayName: String) {
        ARABIC("Arabic"),
        ENGLISH("English")
    }
}