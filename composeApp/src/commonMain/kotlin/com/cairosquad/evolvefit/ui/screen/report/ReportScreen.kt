package com.cairosquad.evolvefit.ui.screen.report

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cairosquad.evolvefit.design_system.theme.AppTheme

import com.cairosquad.evolvefit.ui.navigation.NavBarRoute
import com.cairosquad.evolvefit.ui.navigation.navBar.Scaffold
import com.cairosquad.evolvefit.ui.screen.report.content.ReportScreenContent
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.ui.util.PdfReportManager
import com.cairosquad.evolvefit.viewmodel.report.ReportEffect
import com.cairosquad.evolvefit.viewmodel.report.ReportInteractionListener
import com.cairosquad.evolvefit.viewmodel.report.ReportScreenState
import com.cairosquad.evolvefit.viewmodel.report.ReportViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ReportScreen(
    navigateToWorkoutHistory: () -> Unit,
    onSelectNavBarRoute: (navBarRoute: NavBarRoute) -> Unit,
    viewModel: ReportViewModel = koinViewModel()
) {
    val uiState by viewModel.screenState.collectAsStateWithLifecycle()

    ObserveAsEffect(viewModel.effect) { effect ->
        when (effect) {
            ReportEffect.NavigateToAllHistoryWorkouts -> navigateToWorkoutHistory
            ReportEffect.OnShareClicked -> {
                PdfReportManager.generateAndShareReport(
                    name = uiState.profile.name,
                    report = uiState.report,
                    startDate = uiState.startDate,
                    endDate = uiState.endDate
                )
            }
        }
    }

    Scaffold(
        currentRoute = NavBarRoute.Report,
        onSelectNavBarRoute = onSelectNavBarRoute
    ) {
        ReportScreenContent(
            screenState = uiState,
            listener = viewModel
        )
    }
}

@Preview
@Composable
private fun ReportScreenPreview() {
    AppTheme(isDarkTheme = true) {
        ReportScreenContent(
            ReportScreenState(),
            listener = object : ReportInteractionListener {
                override fun onViewAllHistoryWorkoutsClicked() {}
                override fun onShareClicked() {}
                override fun onDropDownMenuClicked() {}
                override fun onDropDownMenuDismiss() {}
                override fun onDropDownMenuItemClicked(item: ReportScreenState.WeekItem) {}
                override fun onRefresh() {}
            }
        )
    }
}