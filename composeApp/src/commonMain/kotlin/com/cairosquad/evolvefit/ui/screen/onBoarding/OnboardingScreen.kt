package com.cairosquad.evolvefit.ui.screen.onBoarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.navigation.NavController
import com.cairosquad.evolvefit.design_system.component.BottomSheet
import com.cairosquad.evolvefit.design_system.component.CheckboxItem
import com.cairosquad.evolvefit.design_system.component.CheckboxStyle
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.navigation.LoginRoute
import com.cairosquad.evolvefit.ui.navigation.RegisterRoute
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.ui.util.noRippleClickable
import org.jetbrains.compose.ui.tooling.preview.Preview
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
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun OnboardingScreen(
    viewmodel: OnBoardingViewModel = koinViewModel(),
    navController: NavController
) {
    val state by viewmodel.state.collectAsStateWithLifecycle()
    ObserveAsEffect(viewmodel.effect) { effect ->
        when (effect) {
            OnboardingScreenEffect.NavigateToLogin -> {
                navController.navigate(LoginRoute)
            }
            OnboardingScreenEffect.NavigateToRegister -> {
                navController.navigate(RegisterRoute)
            }
        }
    }
    OnboardingScreenContent(
        state = state,
        onSignupClick = viewmodel::onSignUpClicked,
        onLoginClick = viewmodel::onLoginClicked,
        onToggleBottomSheet = viewmodel::toggleBottomSheet,
        onLanguageSelected = viewmodel::onChangeLanguage,
        onConfirmClick = viewmodel::onConfirmClicked
    )
}

@Composable
fun OnboardingScreenContent(
    state: OnboardingScreenState,
    modifier: Modifier = Modifier,
    onSignupClick: () -> Unit,
    onLoginClick: () -> Unit,
    onToggleBottomSheet: (isOpen: Boolean) -> Unit,
    onLanguageSelected: (language: OnboardingScreenState.Language) -> Unit,
    onConfirmClick: () -> Unit
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
        Row(
            modifier = Modifier.align(Alignment.TopEnd).statusBarsPadding().height(48.dp)
                .wrapContentWidth()
                .noRippleClickable { onToggleBottomSheet(state.isBottomSheetOpen.not()) },
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = state.selectedLanguage.displayName,
                style = Theme.textStyle.label.mediumMedium16,
                color = Theme.color.surfaces.textColor,
                modifier = Modifier.padding(top = 14.5.dp, bottom = 14.5.dp, end = 8.dp)
            )
            Image(
                painter = painterResource(Res.drawable.arrow_down),
                contentDescription = "arrow down",
                modifier = Modifier.padding(
                    end = 16.dp, top = 14.5.dp, bottom = 14.5.dp
                ).size(20.dp)
            )
        }
        //bottom section [ready to start - login]
        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .height(48.dp).background(
                        Theme.color.brand.primary,
                    ).fillMaxWidth().noRippleClickable(onClick = onSignupClick),
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
                    modifier = Modifier.padding(end = 4.dp).noRippleClickable(onClick = onLoginClick)
                )

            }

        }

        BottomSheet(
            isVisible = state.isBottomSheetOpen,
            onDismiss = {
                onToggleBottomSheet(false)
            },
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
                            text = language.displayName,
                            isChecked = language == state.bottomSheetSelectedLanguage,
                            onCheckedChange = { onLanguageSelected(language) },
                            style = CheckboxStyle.Tick,
                            modifier = modifier.padding(
                                bottom = 12.dp
                            )
                        )
                    }
                    PrimaryButton(
                        text = "Confirm",
                        onClick = { onConfirmClick() },
                        modifier = Modifier.padding(
                            start = 16.dp, end = 16.dp, bottom = 16.dp , top = 28.dp
                        )
                    )
                }
            },
        )

    }
}

@Preview
@Composable
fun OnboardingScreenContentPreview() {
    var state = OnboardingScreenState().copy(isBottomSheetOpen = true,)
    AppTheme(
        isDarkTheme = true
    ) {
        OnboardingScreenContent(
            state,
            onSignupClick = { },
            onLoginClick = { },
            onToggleBottomSheet = {state.isBottomSheetOpen.not()},
            onLanguageSelected = {  },
            onConfirmClick = {}
        )
    }

}
