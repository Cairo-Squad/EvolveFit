package com.cairosquad.evolvefit.domain.repository

import com.cairosquad.evolvefit.domain.entity.Report

interface ReportRepository {
    suspend fun getReport(): Report
}