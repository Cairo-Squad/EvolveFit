package com.cairosquad.evolvefit.ui.util

import com.cairosquad.evolvefit.viewmodel.report.ReportScreenState

expect object PdfReportManager {
    fun generateAndShareReport(
        report: ReportScreenState.ReportUiState,
        name: String,
        startDate: String,
        endDate: String
    )

    fun createPDF(
        platformContext: Any?,
        report: ReportScreenState.ReportUiState,
        name: String,
        startDate: String,
        endDate: String,
        logoBytes: ByteArray
    ): String

    fun loadLogoBytes(platformContext: Any?, resourceName: String): ByteArray
}