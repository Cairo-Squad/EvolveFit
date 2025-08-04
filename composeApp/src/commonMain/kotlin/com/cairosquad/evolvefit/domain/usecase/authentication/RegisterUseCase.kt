package com.cairosquad.evolvefit.domain.usecase.authentication

import com.cairosquad.evolvefit.domain.AuthRepository
import com.cairosquad.evolvefit.entity.AuthTokens
import com.cairosquad.evolvefit.entity.User

class RegisterUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(user: User): AuthTokens {
        return repository.register(user)
    }
}
