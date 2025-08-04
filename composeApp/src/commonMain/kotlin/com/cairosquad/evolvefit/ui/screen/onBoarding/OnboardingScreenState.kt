package com.cairosquad.evolvefit.ui.screen.onBoarding

import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.arabic
import evolvefit.composeapp.generated.resources.english
import org.jetbrains.compose.resources.StringResource

data class OnboardingScreenState(
    val selectedLanguage: Language = Language.ENGLISH,
    val isBottomSheetOpen: Boolean = false,
    val bottomSheetSelectedLanguage : Language = Language.ENGLISH
) {
    enum class Language( val displayNameRes: StringResource) {
        ARABIC(Res.string.arabic),
        ENGLISH(Res.string.english)
    }
}