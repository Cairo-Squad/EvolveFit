package com.cairosquad.evolvefit.ui.screen.report.content

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.appbar.ActionIconButton
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.report.ReportInteractionListener
import com.cairosquad.evolvefit.viewmodel.report.ReportScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_export
import evolvefit.composeapp.generated.resources.workout_report
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun ReportScreenContent(
    screenState: ReportScreenState,
    listener: ReportInteractionListener,
) {

    Column(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.statusBars)
            .background(Theme.color.surfaces.surface)
    ) {
        CustomAppBar(
            modifier = Modifier.padding(horizontal = 16.dp),
            title = stringResource(Res.string.workout_report),
            tail = {
                if (screenState.reportScreenState == ReportScreenState.SectionStatus.SUCCESS) {
                    ActionIconButton(
                        icon = painterResource(Res.drawable.ic_export),
                        contentDescription = "",
                        onClick = listener::onShareClicked,
                        tint = Theme.color.surfaces.onSurface,
                    )
                }
            }
        )
        Crossfade(
            targetState = screenState.reportScreenState,
            animationSpec = tween(
                durationMillis = 400,
                easing = FastOutSlowInEasing
            )
        ) { screenStatus ->
            when (screenStatus) {

                ReportScreenState.SectionStatus.LOADING -> {
                    ReportScreenLoading()
                }

                ReportScreenState.SectionStatus.SUCCESS -> {
                    ReportScreenSuccess(
                        screenState = screenState,
                        listener = listener
                    )
                }

                ReportScreenState.SectionStatus.ERROR -> {
                    ReportScreenError(
                        listener = listener
                    )
                }
            }
        }
    }
}
