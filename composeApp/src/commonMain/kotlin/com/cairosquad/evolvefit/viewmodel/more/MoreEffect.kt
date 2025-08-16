package com.cairosquad.evolvefit.viewmodel.more

import com.cairosquad.evolvefit.domain.model.Language

sealed class MoreEffect {
    object NavigateToFavorites : MoreEffect()
    object Logout : MoreEffect()
    object NavigateToEditProfile : MoreEffect()
    object NavigateToNotificationSettings : MoreEffect()
    data class ChangeLanguage(val language: Language) : MoreEffect()
    data class ChangeTheme(val theme: MoreScreenState.Theme) : MoreEffect()

}
