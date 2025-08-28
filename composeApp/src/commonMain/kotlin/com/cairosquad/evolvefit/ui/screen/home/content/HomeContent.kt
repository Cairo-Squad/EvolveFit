package com.cairosquad.evolvefit.ui.screen.home.content

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.SnackBar
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.component.RefreshBox
import com.cairosquad.evolvefit.viewmodel.home.HomeInteractionListener
import com.cairosquad.evolvefit.viewmodel.home.HomeScreenState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun HomeContent(
    state: HomeScreenState,
    listener: HomeInteractionListener,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surface)
    ) {
        RefreshBox(
            isRefreshing = state.isRefreshing,
            onRefresh = { listener.onRefresh() }
        ) {
            Crossfade(
                targetState = state.screenStatus,
                animationSpec = tween(
                    durationMillis = 400,
                    easing = FastOutSlowInEasing
                )
            ) {
                when (state.screenStatus) {
                    HomeScreenState.ScreenStatus.SUCCESS -> {
                        HomeSuccessContent(
                            state = state,
                            listener = listener
                        )
                    }

                    HomeScreenState.ScreenStatus.LOADING -> {
                        HomeLoadingScreen()
                    }

                    HomeScreenState.ScreenStatus.FAIL -> {
                        HomeErrorContent(
                            onRetry = listener::onRetryClicked
                        )
                    }
                }
            }
        }
        SnackBar(
            text = stringResource(state.snackBarState.messageRes),
            isVisible = state.snackBarState.isVisible,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp),
            icon = painterResource(state.snackBarState.iconRes),
            iconTint = Theme.color.brand.primary,
            paddingBottom = 0.dp,
            addNavBarPadding = false
        )
    }
}
