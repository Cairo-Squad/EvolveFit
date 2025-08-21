package com.cairosquad.evolvefit.repository.profile

import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.repository.ProfileRepository
import com.cairosquad.evolvefit.repository.execption.callDataSource
import com.cairosquad.evolvefit.repository.profile.dto.toProfileRequest
import com.cairosquad.evolvefit.repository.profile.remote.RemoteProfileDataSource

class ProfileRepositoryImpl(
    private val remoteProfileDataSource: RemoteProfileDataSource
) : ProfileRepository {
    override suspend fun getProfile(): Profile {
        return callDataSource { remoteProfileDataSource.getProfile().toEntity() }
    }

    override suspend fun editProfile(profile: Profile): Profile {
        return callDataSource { remoteProfileDataSource.editProfile(profile.toProfileRequest()).toEntity() }
    }

    override suspend fun uploadProfileImage(
        fileBytes: ByteArray,
        fileName: String
    ): String {
        return callDataSource { remoteProfileDataSource.uploadProfileImage(fileBytes, fileName) }
    }
}