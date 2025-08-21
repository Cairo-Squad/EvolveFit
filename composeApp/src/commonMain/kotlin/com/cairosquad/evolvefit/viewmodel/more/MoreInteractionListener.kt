package com.cairosquad.evolvefit.viewmodel.more

import com.cairosquad.evolvefit.domain.model.Language

interface MoreInteractionListener {
    fun onClickPersonInformation()
    fun onClickFavorites()
    fun onClickNotification()
    fun onClickTheme()
    fun onClickLanguage()
    fun onClickLogout()
    fun onDismissThemeBottomSheet()
    fun onDismissLanguageBottomSheet()
    fun onDismissLogoutBottomSheet()
    fun onConfirmChangeTheme(theme: MoreScreenState.Theme)
    fun onConfirmChangeLanguage(language: Language)
    fun onLogout()
    fun onSelectTheme(theme: MoreScreenState.Theme)
    fun onSelectLanguage(language: Language)

}