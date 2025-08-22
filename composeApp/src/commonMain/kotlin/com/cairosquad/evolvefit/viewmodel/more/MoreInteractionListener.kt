package com.cairosquad.evolvefit.viewmodel.more

import com.cairosquad.evolvefit.domain.model.Language

interface MoreInteractionListener {
    fun onPersonInformationClicked()
    fun onFavoritesClicked()
    fun onNotificationClicked()
    fun onThemeClicked()
    fun onLanguageClicked()
    fun onLogoutClicked()
    fun onDismissThemeBottomSheet()
    fun onDismissLanguageBottomSheet()
    fun onDismissLogoutBottomSheet()
    fun onConfirmChangeTheme(theme: MoreScreenState.Theme)
    fun onConfirmChangeLanguage(language: Language)
    fun onLogout()
    fun onSelectTheme(theme: MoreScreenState.Theme)
    fun onSelectLanguage(language: Language)

}