package com.cairosquad.evolvefit.viewmodel.report

interface ReportInteractionListener {

    fun onViewAllHistoryWorkoutsClicked()
    fun onShareClicked()
    fun onDropDownMenuClicked()
    fun onDropDownMenuDismiss()
    fun onDropDownMenuItemClicked(item: ReportScreenState.WeekItem)
    fun onRefresh()
}