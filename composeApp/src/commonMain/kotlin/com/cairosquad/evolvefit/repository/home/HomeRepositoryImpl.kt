package com.cairosquad.evolvefit.repository.home

import com.cairosquad.evolvefit.domain.repository.HomeRepository
import com.cairosquad.evolvefit.domain.model.User
import com.cairosquad.evolvefit.domain.model.WeeklyProgress

class HomeRepositoryImpl : HomeRepository {
    override suspend fun getUser(): User {
        TODO("Not yet implemented")
    }

    override suspend fun getWeeklyProgress(): WeeklyProgress {
        TODO("Not yet implemented")
    }
}