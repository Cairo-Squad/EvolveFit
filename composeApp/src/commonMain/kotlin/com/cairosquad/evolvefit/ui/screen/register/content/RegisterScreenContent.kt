package com.cairosquad.evolvefit.ui.screen.register.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.register.RegisterInteractionListener
import com.cairosquad.evolvefit.viewmodel.register.RegisterScreenState
import com.cairosquad.evolvefit.viewmodel.register.RegisterViewModel

@Composable
fun RegisterScreenContent(
    state: RegisterScreenState,
    listener: RegisterInteractionListener,
){

    val pagerState = rememberPagerState(state.currentStep, pageCount = { RegisterViewModel.MAX_STEPS })

    LaunchedEffect(state.currentStep) {
        println("currentStep LaunchedEffect state.currentStep: ${state.currentStep}")
        if (state.currentStep == pagerState.currentPage) return@LaunchedEffect
        pagerState.animateScrollToPage(state.currentStep)
    }
    LaunchedEffect(pagerState.currentPage) {
        println("currentStep LaunchedEffect pagerState.currentPage: ${pagerState.currentPage}")
        if (state.currentStep == pagerState.currentPage) return@LaunchedEffect

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.color.surfaces.surface)
            .statusBarsPadding()
            .navigationBarsPadding(),
    ) {
        Button(
            modifier = Modifier.padding(12.dp),
            onClick = listener::onClickBack
        ){
            Text("Back")
        }

        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            state = pagerState
        ){ pageIndex ->
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
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(12.dp),
            onClick = listener::onClickNext
        ){
            Text("Next")
        }
    }
}