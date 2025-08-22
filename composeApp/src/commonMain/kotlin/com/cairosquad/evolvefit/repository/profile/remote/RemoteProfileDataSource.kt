package com.cairosquad.evolvefit.repository.profile.remote

import com.cairosquad.evolvefit.repository.profile.remote.dto.ProfileGetDto
import com.cairosquad.evolvefit.repository.profile.remote.dto.ProfilePostDto

interface RemoteProfileDataSource {
    suspend fun getProfile(): ProfileGetDto
    suspend fun editProfile(profileRequest: ProfilePostDto): ProfileGetDto
    suspend fun uploadProfileImage(fileBytes: ByteArray, fileName: String): String
}