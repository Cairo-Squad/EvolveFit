package com.cairosquad.evolvefit.domain.usecase.authentication

import com.cairosquad.evolvefit.domain.AuthRepository
import com.cairosquad.evolvefit.entity.User

class RegisterUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(data: User): Result<Unit> {
        return authRepository.register(data)
    }
}
