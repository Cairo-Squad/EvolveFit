package com.cairosquad.evolvefit.repository.profile.remote

import com.cairosquad.evolvefit.repository.profile.dto.ProfileResponse

interface RemoteProfileDataSource {
    suspend fun getProfile(): ProfileResponse

}