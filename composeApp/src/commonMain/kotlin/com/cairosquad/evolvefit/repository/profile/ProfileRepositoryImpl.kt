package com.cairosquad.evolvefit.repository.profile

import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.model.WeekDay
import com.cairosquad.evolvefit.domain.repository.ProfileRepository
import com.cairosquad.evolvefit.repository.execption.callDataSource
import com.cairosquad.evolvefit.repository.profile.remote.RemoteProfileDataSource

class ProfileRepositoryImpl(
    private val remoteProfileDataSource: RemoteProfileDataSource
) : ProfileRepository {
    override suspend fun getProfile(): Profile {
        return callDataSource { remoteProfileDataSource.getProfile().toEntity() }
    }

    override suspend fun getUserWorkoutDays(): Set<WeekDay> {
        TODO("Not yet implemented")
    }

    override suspend fun editUserWorkoutDays() {
        TODO("Not yet implemented")
    }
}