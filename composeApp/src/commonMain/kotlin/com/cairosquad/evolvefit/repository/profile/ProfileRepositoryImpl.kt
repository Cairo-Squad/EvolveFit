package com.cairosquad.evolvefit.repository.profile

import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.repository.ProfileRepository
import com.cairosquad.evolvefit.repository.execption.callDataSource
import com.cairosquad.evolvefit.repository.profile.remote.ProfileRemoteDataSource
import com.cairosquad.evolvefit.repository.profile.remote.toProfileRequest

class ProfileRepositoryImpl(
    private val profileRemoteDataSource: ProfileRemoteDataSource
) : ProfileRepository {
    override suspend fun getProfile(): Profile {
        return callDataSource { profileRemoteDataSource.getProfile().toEntity() }
    }

    override suspend fun editProfile(profile: Profile): Profile {
        return callDataSource {
            profileRemoteDataSource.editProfile(profile.toProfileRequest())
        }.toEntity()
    }

    override suspend fun uploadProfileImage(
        fileBytes: ByteArray,
        fileName: String
    ): String {
        return callDataSource { profileRemoteDataSource.uploadProfileImage(fileBytes, fileName) }
    }
}