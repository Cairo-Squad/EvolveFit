package com.cairosquad.evolvefit.repository.home

import com.cairosquad.evolvefit.domain.repository.HomeRepository
import com.cairosquad.evolvefit.domain.usecase.home.model.User
import com.cairosquad.evolvefit.domain.usecase.home.model.WeeklyProgress

class HomeRepositoryImpl : HomeRepository {
    override suspend fun getUser(): User {
        TODO("Not yet implemented")
    }

    override suspend fun getWeeklyProgress(): WeeklyProgress {
        TODO("Not yet implemented")
    }
}