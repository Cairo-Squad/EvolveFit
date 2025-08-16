package com.cairosquad.evolvefit.viewmodel.more

import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.model.Language
import com.cairosquad.evolvefit.domain.usecase.authentication.AuthenticationUseCase
import com.cairosquad.evolvefit.domain.usecase.profile.ManageLanguageUseCase
import com.cairosquad.evolvefit.domain.usecase.profile.ManageProfileUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel

class MoreViewModel(
    private val manageLanguageUseCase: ManageLanguageUseCase,
    private val manageProfileUseCase: ManageProfileUseCase,
    private val authenticationUseCase: AuthenticationUseCase
) : BaseViewModel<MoreScreenState, MoreEffect>(
    initialState = MoreScreenState()
), MoreInteractionListener {
    init {
        loadProfile()
    }

    private fun loadProfile() {
        tryToCall(
            onStart = { updateState { it.copy(isLoading = true) } },
            block = { manageProfileUseCase.getProfile() },
            onSuccess = ::onSuccessLoadProfile,
            onError = { },
            onEnd = { updateState { it.copy(isLoading = false) } },
        )
    }

    private fun onSuccessLoadProfile(profile: Profile) {
        manageLanguageUseCase.save(profile.preferredLanguage)
        updateState { it.copy(profile = profile.toUiState()) }
        sendEffect(MoreEffect.ChangeLanguage(profile.preferredLanguage))
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
        tryToCall(
            block = {
                manageLanguageUseCase.save(language)
                language
            },
            onSuccess = ::onSuccessChangeLanguage,
            onError = { },
            onStart = { updateState { it.copy(isLoading = true) } },
            onEnd = { updateState { it.copy(isLoading = false) } },
        )
    }

    private fun onSuccessChangeLanguage(updatedLanguage: Language) {
        updateState {
            it.copy(
                profile = it.profile.copy(preferredLanguage = updatedLanguage),
                isLanguageBottomSheetEnabled = false
            )
        }
        sendEffect(MoreEffect.ChangeLanguage(updatedLanguage))
    }

    override fun onLogout() {
        tryToCall(
            block = { authenticationUseCase.logout() },
            onSuccess = ::onSuccessfulLogout,
            onError = { },
            onStart = { updateState { it.copy(isLoading = true) } },
            onEnd = { updateState { it.copy(isLoading = false) } },
        )
    }

    private fun onSuccessfulLogout(unit: Unit) {
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
        updateState {
            it.copy(
                currentTheme = theme,
                isThemeBottomSheetEnabled = false
            )
        }
        sendEffect(MoreEffect.ChangeTheme(theme))
    }
}