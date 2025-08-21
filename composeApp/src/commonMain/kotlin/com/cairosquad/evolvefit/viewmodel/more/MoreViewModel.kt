package com.cairosquad.evolvefit.viewmodel.more

import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.model.Language
import com.cairosquad.evolvefit.domain.usecase.authentication.AuthenticationUseCase
import com.cairosquad.evolvefit.domain.usecase.profile.ManageProfileUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.failed_to_logout
import evolvefit.composeapp.generated.resources.failed_to_update_user_profile

class MoreViewModel(
    private val manageProfileUseCase: ManageProfileUseCase,
    private val authenticationUseCase: AuthenticationUseCase,
    savedTheme: MoreScreenState.Theme,
    currentLanguage: Language
) : BaseViewModel<MoreScreenState, MoreEffect>(
    MoreScreenState(
        currentTheme = savedTheme,
        isDarkChecked = savedTheme == MoreScreenState.Theme.DARK,
        currentLanguage = currentLanguage,
        isEnglishChecked = currentLanguage == Language.ENGLISH
    )
),
    MoreInteractionListener {
    init {
        loadProfile()
    }

    private fun loadProfile() {
        tryToCall(
            onStart = { updateState { it.copy(isLoading = true) } },
            block = { manageProfileUseCase.getProfile() },
            onSuccess = ::onSuccessLoadProfile,
            onError = { onLoadProfileFailed() },
            onEnd = { updateState { it.copy(isLoading = false) } },
        )
    }

    override fun onClickPersonInformation() {
        sendEffect(MoreEffect.NavigateToEditProfile)
    }

    override fun onClickFavorites() {
        sendEffect(MoreEffect.NavigateToFavorites)
    }

    override fun onClickNotification() {
        sendEffect(MoreEffect.NavigateToNotificationSettings)
    }

    override fun onClickTheme() {
        updateState { it.copy(isThemeBottomSheetEnabled = true) }
    }

    override fun onDismissThemeBottomSheet() {
        updateState { it.copy(isThemeBottomSheetEnabled = false) }
    }

    override fun onClickLanguage() {
        updateState { it.copy(isLanguageBottomSheetEnabled = true) }
    }

    override fun onConfirmChangeLanguage(language: Language) {
        updateState {
            it.copy(
                currentLanguage = language,
                isEnglishChecked = language == Language.ENGLISH,
                isLanguageBottomSheetEnabled = false
            )
        }
        sendEffect(MoreEffect.ChangeLanguage(languageToLanguageCode(language)))
    }

    override fun onLogout() {
        tryToCall(
            onStart = { updateState { it.copy(isLoading = true) } },
            block = { authenticationUseCase.logout() },
            onSuccess = { onSuccessfulLogout() },
            onError = { onLogoutFailed() },
            onEnd = { updateState { it.copy(isLoading = false) } },
        )
    }

    override fun onSelectTheme(theme: MoreScreenState.Theme) {
        updateState {
            it.copy(
                currentTheme = theme,
                isDarkChecked = theme == MoreScreenState.Theme.DARK
            )
        }
    }

    override fun onSelectLanguage(language: Language) {
        updateState {
            it.copy(
                currentLanguage = language,
                isEnglishChecked = language == Language.ENGLISH
            )
        }
    }

    private fun onSuccessfulLogout() {
        updateState { it.copy(isLogoutBottomSheetEnabled = false) }
        sendEffect(MoreEffect.Logout)
    }

    override fun onDismissLanguageBottomSheet() {
        updateState { it.copy(isLanguageBottomSheetEnabled = false) }
    }

    override fun onClickLogout() {
        updateState { it.copy(isLogoutBottomSheetEnabled = true) }
    }

    override fun onDismissLogoutBottomSheet() {
        updateState { it.copy(isLogoutBottomSheetEnabled = false) }
    }

    override fun onConfirmChangeTheme(theme: MoreScreenState.Theme) {
        updateState { it.copy(currentTheme = theme, isThemeBottomSheetEnabled = false) }
        sendEffect(MoreEffect.ChangeTheme(theme))
    }

    private fun onLogoutFailed() {
        updateState { it.copy(errorMessage = Res.string.failed_to_logout) }
    }

    private fun onLoadProfileFailed() {
        updateState { it.copy(errorMessage = Res.string.failed_to_update_user_profile) }
    }

    private fun onSuccessLoadProfile(profile: Profile) {
        updateState { it.copy(profile = profile.toUiState()) }
    }
}