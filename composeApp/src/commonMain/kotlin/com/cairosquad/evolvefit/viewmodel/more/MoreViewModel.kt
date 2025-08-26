package com.cairosquad.evolvefit.viewmodel.more

import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.model.Language
import com.cairosquad.evolvefit.domain.usecase.authentication.AuthenticationUseCase
import com.cairosquad.evolvefit.domain.usecase.profile.ManagePreferencesUseCase
import com.cairosquad.evolvefit.domain.usecase.profile.ManageProfileUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.failed_to_logout
import evolvefit.composeapp.generated.resources.failed_to_update_user_profile

class MoreViewModel(
    private val manageProfileUseCase: ManageProfileUseCase,
    private val authenticationUseCase: AuthenticationUseCase,
    private val managePreferencesUseCase: ManagePreferencesUseCase
) : BaseViewModel<MoreScreenState, MoreEffect>(MoreScreenState()), MoreInteractionListener {
    init {
        loadThemePreferences()
        loadLanguagePreferences()
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

    private fun loadThemePreferences() {
        tryToCall(
            block = { managePreferencesUseCase.getTheme() },
            onSuccess = { theme -> updateState { it.copy(currentTheme = theme) } },
            onError = { },
        )
    }

    private fun loadLanguagePreferences() {
        tryToCall(
            block = { managePreferencesUseCase.getLanguage() },
            onSuccess = { languageCode ->
                updateState {
                    it.copy(
                        currentLanguage = languageCodeToLanguage(
                            languageCode
                        )
                    )
                }
            },
            onError = { },
        )
    }

    override fun onPersonInformationClicked() {
        sendEffect(MoreEffect.NavigateToEditProfile)
    }

    override fun onFavoritesClicked() {
        sendEffect(MoreEffect.NavigateToFavorites)
    }

    override fun onNotificationClicked() {
        sendEffect(MoreEffect.NavigateToNotificationSettings)
    }

    override fun onThemeClicked() {
        updateState { it.copy(isThemeBottomSheetEnabled = true) }
    }

    override fun onDismissThemeBottomSheet() {
        updateState { it.copy(isThemeBottomSheetEnabled = false) }
    }

    override fun onLanguageClicked() {
        updateState { it.copy(isLanguageBottomSheetEnabled = true) }
    }

    override fun onConfirmChangeLanguage(language: Language) {
        tryToCall(
            block = { managePreferencesUseCase.saveLanguage(languageToLanguageCode(language)) },
            onSuccess = { onSuccessChangeLanguage() },
            onError = {},
        )
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
                isDarkChecked = theme == MoreScreenState.Theme.DARK
            )
        }
    }

    override fun onSelectLanguage(language: Language) {
        updateState {
            it.copy(
                isEnglishChecked = language == Language.ENGLISH
            )
        }
    }

    override fun onDismissLanguageBottomSheet() {
        updateState { it.copy(isLanguageBottomSheetEnabled = false) }
    }

    override fun onLogoutClicked() {
        updateState { it.copy(isLogoutBottomSheetEnabled = true) }
    }

    override fun onDismissLogoutBottomSheet() {
        updateState { it.copy(isLogoutBottomSheetEnabled = false) }
    }

    override fun onConfirmChangeTheme(theme: MoreScreenState.Theme) {
        tryToCall(
            block = { managePreferencesUseCase.saveTheme(theme) },
            onSuccess = {
                onSuccessChangeTheme()
                sendEffect(MoreEffect.ChangeTheme(theme))
            },
            onError = {}
        )
    }

    fun onReturnToScreen() {
        loadProfile()
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

    private fun onSuccessChangeLanguage() {
        val languageCode = managePreferencesUseCase.getLanguage()
        val language = languageCodeToLanguage(languageCode)
        updateState {
            it.copy(
                currentLanguage = language,
                isEnglishChecked = language == Language.ENGLISH,
                isLanguageBottomSheetEnabled = false
            )
        }
    }

    private fun onSuccessChangeTheme() {
        val theme = managePreferencesUseCase.getTheme()
        updateState {
            it.copy(
                currentTheme = theme,
                isThemeBottomSheetEnabled = false,
                isDarkChecked = theme == MoreScreenState.Theme.DARK
            )
        }
    }

    private fun onSuccessfulLogout() {
        updateState { it.copy(isLogoutBottomSheetEnabled = false) }
        sendEffect(MoreEffect.Logout)
    }

}