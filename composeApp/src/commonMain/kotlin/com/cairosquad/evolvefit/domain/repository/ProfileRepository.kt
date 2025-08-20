package com.cairosquad.evolvefit.domain.repository

import com.cairosquad.evolvefit.domain.entity.Profile

interface ProfileRepository {
    suspend fun getProfile(): Profile
    suspend fun editProfile(profile: Profile): Profile
    suspend fun uploadProfileImage(fileBytes: ByteArray, fileName: String): String
}