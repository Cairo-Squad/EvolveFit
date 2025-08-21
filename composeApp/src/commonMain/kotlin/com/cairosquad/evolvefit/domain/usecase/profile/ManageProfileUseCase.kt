package com.cairosquad.evolvefit.domain.usecase.profile

import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.repository.ProfileRepository

class ManageProfileUseCase(
    private val profileRepository: ProfileRepository,
) {
    suspend fun getProfile(): Profile {
        return profileRepository.getProfile()
    }

    suspend fun editProfile(profile: Profile): Profile {
        return profileRepository.editProfile(profile)
    }

    suspend fun uploadProfileImage(fileBytes: ByteArray, fileName: String): String {
        return profileRepository.uploadProfileImage(fileBytes, fileName)
    }
}