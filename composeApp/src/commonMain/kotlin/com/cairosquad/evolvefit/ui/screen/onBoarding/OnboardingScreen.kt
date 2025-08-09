package com.cairosquad.evolvefit.ui.screen.onBoarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cairosquad.evolvefit.design_system.component.BottomSheet
import com.cairosquad.evolvefit.design_system.component.CheckboxItem
import com.cairosquad.evolvefit.design_system.component.CheckboxStyle
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.ui.util.noRippleClickable
import evolvefit.composeapp.generated.resources.Onboarding
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.arrow_down
import evolvefit.composeapp.generated.resources.choose_language
import evolvefit.composeapp.generated.resources.do_you_have_an_account
import evolvefit.composeapp.generated.resources.language_selection_description
import evolvefit.composeapp.generated.resources.login
import evolvefit.composeapp.generated.resources.ready_to_start
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun OnboardingScreen(
    viewmodel: OnBoardingViewModel = koinViewModel(),
    navigateToRegister: () -> Unit,
    navigateToLogin: () -> Unit,
) {
    val state by viewmodel.screenState.collectAsStateWithLifecycle()
    ObserveAsEffect(viewmodel.effect) { effect ->
        when (effect) {
            OnboardingScreenEffect.NavigateToLogin -> {
                navigateToLogin()
            }
            OnboardingScreenEffect.NavigateToRegister -> {
                navigateToRegister()
            }
        }
    }
    OnboardingScreenContent(
        state = state, listener = viewmodel
    )
}

@Composable
fun OnboardingScreenContent(
    state: OnboardingScreenState,
    listener: OnboardingScreenListener,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(Res.drawable.Onboarding),
            contentDescription = "onboarding image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        LanguageSelectionButton(
            state = state,
            onToggleBottomSheet = listener::toggleBottomSheet,
            modifier = Modifier.align(Alignment.TopEnd)
        )
        BottomActionSection(
            onLoginClicked = listener::onLoginClicked,
            onSignUpClicked = listener::onSignUpClicked,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
        )

        LanguageBottomSheet(
            isVisible = state.isBottomSheetOpen,
            selectedLanguage = state.selectedLanguage,
            onDismiss = { listener.toggleBottomSheet(false) },
            onLanguageSelected = listener::onChangeLanguage,
            onConfirmClick = listener::onConfirmClicked,
            modifier = Modifier
        )
    }
}

@Composable
private fun BottomActionSection(
    onLoginClicked: () -> Unit,
    onSignUpClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                .clip(RoundedCornerShape(24.dp))
                .height(48.dp).background(Theme.color.brand.primary)
                .fillMaxWidth()
                .noRippleClickable(onClick = { onSignUpClicked() }),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(Res.string.ready_to_start),
                style = Theme.textStyle.body.mediumMedium14,
                color = Theme.color.brand.onPrimary
            )
        }

        Row(
            modifier = Modifier.padding(bottom = 24.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(Res.string.do_you_have_an_account),
                style = Theme.textStyle.label.mediumMedium14,
                color = Theme.color.surfaces.textColor,
                modifier = Modifier.padding(end = 4.dp)
            )
            Text(
                text = stringResource(Res.string.login),
                style = Theme.textStyle.label.mediumMedium16,
                color = Theme.color.brand.primary,
                modifier = Modifier.padding(end = 4.dp)
                    .noRippleClickable(onClick = { onLoginClicked() })
            )
        }
    }
}

@Composable
private fun LanguageSelectionButton(
    state: OnboardingScreenState,
    onToggleBottomSheet: (isOpen: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.statusBarsPadding().height(48.dp).wrapContentWidth()
            .noRippleClickable { onToggleBottomSheet(state.isBottomSheetOpen.not()) },
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = stringResource(state.selectedLanguage.displayNameRes),
            style = Theme.textStyle.label.mediumMedium16,
            color = Theme.color.surfaces.textColor,
            modifier = Modifier.padding(top = 14.5.dp, bottom = 14.5.dp, end = 8.dp)
        )
        Image(
            painter = painterResource(Res.drawable.arrow_down),
            contentDescription = "arrow down",
            modifier = Modifier.padding(end = 16.dp, top = 14.5.dp, bottom = 14.5.dp).size(20.dp)
        )
    }
}


@Composable
private fun LanguageBottomSheet(
    isVisible: Boolean,
    selectedLanguage: OnboardingScreenState.Language,
    onDismiss: () -> Unit,
    onLanguageSelected: (language: OnboardingScreenState.Language) -> Unit,
    onConfirmClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BottomSheet(
        isVisible = isVisible,
        onDismiss = onDismiss,
        content = {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(Res.string.choose_language),
                    style = Theme.textStyle.label.mediumMedium16,
                    color = Theme.color.surfaces.onSurface,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = stringResource(Res.string.language_selection_description),
                    style = Theme.textStyle.label.mediumMedium12,
                    color = Theme.color.surfaces.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                OnboardingScreenState.Language.entries.forEach { language ->
                    CheckboxItem(
                        text = stringResource(language.displayNameRes),
                        isChecked = language == selectedLanguage,
                        onCheckedChange = { onLanguageSelected(language) },
                        style = CheckboxStyle.Tick,
                        modifier = modifier.padding(bottom = 12.dp)
                    )
                }
                PrimaryButton(
                    text = "Confirm", onClick = onConfirmClick, modifier = Modifier.padding(
                        start = 16.dp, end = 16.dp, bottom = 16.dp, top = 28.dp
                    )
                )
            }
        },
    )
}

@Preview
@Composable
private fun OnboardingScreenPreview() {
    OnboardingScreenContent(
        state = OnboardingScreenState(),
        listener = object : OnboardingScreenListener{
            override fun onChangeLanguage(language: OnboardingScreenState.Language) {
                TODO("Not yet implemented")
            }

            override fun toggleBottomSheet(isOpen: Boolean) {
                TODO("Not yet implemented")
            }

            override fun onSignUpClicked() {
                TODO("Not yet implemented")
            }

            override fun onLoginClicked() {
                TODO("Not yet implemented")
            }

            override fun onConfirmClicked() {
                TODO("Not yet implemented")
            }

        }
    )
}
