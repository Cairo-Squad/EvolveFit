package com.cairosquad.evolvefit.viewmodel.report

sealed class ReportEffect {
    data object navigateToAllHistoryWorkouts : ReportEffect()
    data object onShareClicked: ReportEffect()
}