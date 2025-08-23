package com.cairosquad.evolvefit.viewmodel.report

sealed class ReportEffect {
    data object NavigateToAllHistoryWorkouts : ReportEffect()
    data object OnShareClicked: ReportEffect()
}