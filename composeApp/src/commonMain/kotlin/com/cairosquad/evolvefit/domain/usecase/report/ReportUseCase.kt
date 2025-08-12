package com.cairosquad.evolvefit.domain.usecase.report

import com.cairosquad.evolvefit.domain.entity.Report
import com.cairosquad.evolvefit.domain.repository.ReportRepository

class ReportUseCase(
    private val reportRepository: ReportRepository,
) {

    suspend fun getReport(): Report {
        return reportRepository.getReport()
    }
}