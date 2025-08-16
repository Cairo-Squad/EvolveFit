package com.cairosquad.evolvefit.repository.home

import com.cairosquad.evolvefit.domain.repository.HomeRepository
import com.cairosquad.evolvefit.domain.usecase.home.model.WeeklyProgress
import com.cairosquad.evolvefit.repository.execption.callDataSource
import com.cairosquad.evolvefit.repository.home.data_source.remote.RemoteHomeDataSource
import kotlinx.datetime.LocalDate

class HomeRepositoryImpl(
    private val remoteHomeDataSource: RemoteHomeDataSource
) : HomeRepository {
    override suspend fun getWeeklyProgress(
        startDate: LocalDate,
        endDate: LocalDate
    ): WeeklyProgress {
        return callDataSource {
            remoteHomeDataSource.getWeeklyProgress(
                startDate = startDate.toString(),
                endDate = endDate.toString()
            ).toEntity(
                startDate = startDate,
                endDate = endDate
            )
        }
    }
}