package com.cairosquad.evolvefit.ui.screen.register.content

import RegisterScreenContentSelectHeightAndWeight
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.component.appbar.IndicatorBar
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.register.RegisterInteractionListener
import com.cairosquad.evolvefit.viewmodel.register.RegisterScreenState
import com.cairosquad.evolvefit.viewmodel.register.RegisterViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.next
import evolvefit.composeapp.generated.resources.start_now
import org.jetbrains.compose.resources.stringResource

@Composable
fun RegisterScreenContent(
    state: RegisterScreenState,
    listener: RegisterInteractionListener,
) {

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { RegisterViewModel.MAX_STEPS }
    )

    var previousPage by remember { mutableStateOf(0) }

    val currentStepIndex = state.currentStep - 1

    LaunchedEffect(currentStepIndex) {
        if (currentStepIndex == pagerState.currentPage) return@LaunchedEffect
        if (currentStepIndex == previousPage) return@LaunchedEffect
        if (pagerState.currentPage != previousPage) return@LaunchedEffect
        try {
            pagerState.animateScrollToPage(currentStepIndex)
        } finally {
            previousPage = currentStepIndex
        }
    }

    LaunchedEffect(pagerState.isScrollInProgress) {
        if (pagerState.isScrollInProgress) return@LaunchedEffect
        if (currentStepIndex == pagerState.currentPage) return@LaunchedEffect
        if (currentStepIndex != previousPage) return@LaunchedEffect
        if (pagerState.currentPage == previousPage) return@LaunchedEffect
        previousPage = pagerState.currentPage
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.color.surfaces.surface)
            .statusBarsPadding()
            .navigationBarsPadding(),
    ) {
        IndicatorBar(
            currentStep = state.currentStep,
            totalSteps = RegisterViewModel.MAX_STEPS,
            onBackClick = listener::onClickBack,
        )

        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            state = pagerState,
            userScrollEnabled = false
        ) { pageIndex ->
            when (pageIndex) {
                0 -> RegisterScreenContentSelectGender(state, listener)
                1 -> RegisterScreenContentSelectUnitsOfMeasurement(state, listener)
                2 -> RegisterScreenContentSelectHeightAndWeight(state, listener)
                3 -> RegisterScreenContentChooseYourGoal(state, listener)
                4 -> RegisterScreenContentSelectYourTools(state, listener)
                5 -> RegisterScreenContentSelectNotificationSittings(state, listener)
                6 -> RegisterScreenContentSelectWorkoutDays(state, listener)
                7 -> RegisterScreenContentUserNamePasswordDateOfBirth(state, listener)
            }
        }

        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 24.dp),
            text =
                if (state.currentStep == 8) stringResource(Res.string.start_now)
                else stringResource(Res.string.next),
            onClick =
                if (state.currentStep == 8) listener::onClickStartNow
                else listener::onClickNext,
            isEnabled = state.nextButtonEnabled
        )
    }
}