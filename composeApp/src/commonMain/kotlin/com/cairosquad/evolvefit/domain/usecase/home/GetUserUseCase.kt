package com.cairosquad.evolvefit.domain.usecase.home

import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.usecase.profile.ManageProfileUseCase

class GetUserUseCase(
    private val profileUseCase: ManageProfileUseCase
) {

    suspend fun getUser(): Profile {
        return profileUseCase.getProfile()
    }
}