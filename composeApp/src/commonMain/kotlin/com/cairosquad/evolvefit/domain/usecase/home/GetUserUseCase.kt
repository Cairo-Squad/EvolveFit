package com.cairosquad.evolvefit.domain.usecase.home

import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.usecase.profile.ManageProfileUseCase
import com.cairosquad.evolvefit.domain.repository.HomeRepository
import com.cairosquad.evolvefit.domain.model.User

class GetUserUseCase(
    private val profileUseCase: ManageProfileUseCase
) {

    suspend fun getUser(): Profile {
        return profileUseCase.getProfile()
    }
}