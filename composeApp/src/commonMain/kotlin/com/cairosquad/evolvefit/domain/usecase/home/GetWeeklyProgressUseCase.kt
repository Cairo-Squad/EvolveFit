package com.cairosquad.evolvefit.domain.usecase.home

import com.cairosquad.evolvefit.domain.repository.HomeRepository
import com.cairosquad.evolvefit.domain.model.WeeklyProgress

class GetWeeklyProgressUseCase(
    private val homeRepository: HomeRepository
) {

    suspend fun getWeeklyProgress(): WeeklyProgress {
        return homeRepository.getWeeklyProgress()
    }
}