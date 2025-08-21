package com.cairosquad.evolvefit.repository.profile.remote

import com.cairosquad.evolvefit.repository.profile.dto.ProfileResponse
import com.cairosquad.evolvefit.repository.profile.dto.ProfileRequest

interface RemoteProfileDataSource {
    suspend fun getProfile(): ProfileResponse
    suspend fun editProfile(profileRequest: ProfileRequest): ProfileResponse
    suspend fun uploadProfileImage(fileBytes: ByteArray, fileName: String): String
}