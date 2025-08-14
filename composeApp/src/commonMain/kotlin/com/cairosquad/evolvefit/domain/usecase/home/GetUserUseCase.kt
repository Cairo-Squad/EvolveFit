package com.cairosquad.evolvefit.domain.usecase.home

import com.cairosquad.evolvefit.domain.repository.HomeRepository
import com.cairosquad.evolvefit.domain.usecase.home.model.User

class GetUserUseCase(
    private val homeRepository: HomeRepository
) {

    suspend fun getUser(): User {
        return homeRepository.getUser()
    }
}