package com.cairosquad.evolvefit.viewmodel.more

import com.cairosquad.evolvefit.domain.model.Language
import org.jetbrains.compose.resources.StringResource

data class MoreScreenState(
    val isLoading: Boolean = false,
    val isThemeBottomSheetEnabled: Boolean = false,
    val isLanguageBottomSheetEnabled: Boolean = false,
    val isLogoutBottomSheetEnabled: Boolean = false,
    val currentTheme: Theme = Theme.LIGHT,
    val currentLanguage: Language = Language.ENGLISH,
    val profile: Profile = Profile(),
    val tempTheme: Theme? = null,
    val tempLanguage: Language? = null,
    val errorMessage: StringResource? = null
    ) {
    enum class Theme {
        DARK, LIGHT
    }

    data class Profile(
        val name: String = "",
        val email: String = "",
        val image: String = "",
        val height: Float = 0.0f,
        val weight: Float = 0.0f,
        val age: Int = 0,
    )
}