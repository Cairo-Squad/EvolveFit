package com.cairosquad.evolvefit.viewmodel.profile

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
    fun onChangeTheme(theme: MoreScreenState.Theme)
    fun onConfirmChangeLanguage(language: Language)
    fun onLogout()

}