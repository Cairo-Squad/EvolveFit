package com.cairosquad.evolvefit.repository.profile.remote

import com.cairosquad.evolvefit.repository.profile.dto.ProfileDto

interface RemoteProfileDataSource {
    suspend fun getProfile(): ProfileDto
    suspend fun editProfile(profileRequest: ProfileDto): ProfileDto
    suspend fun uploadProfileImage(fileBytes: ByteArray, fileName: String): ProfileDto
}