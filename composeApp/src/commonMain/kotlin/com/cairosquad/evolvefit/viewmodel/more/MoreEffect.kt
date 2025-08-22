package com.cairosquad.evolvefit.viewmodel.more

sealed class MoreEffect {
    object NavigateToFavorites : MoreEffect()
    object Logout : MoreEffect()
    object NavigateToEditProfile : MoreEffect()
    object NavigateToNotificationSettings : MoreEffect()
    data class ChangeLanguage(val languageCode: String) : MoreEffect()
    data class ChangeTheme(val theme: MoreScreenState.Theme) : MoreEffect()

}
