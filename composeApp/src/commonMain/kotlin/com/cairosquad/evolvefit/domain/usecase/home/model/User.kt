package com.cairosquad.evolvefit.domain.usecase.home.model

import com.cairosquad.evolvefit.domain.entity.Profile

data class User(
    val userName: String,
    val userGender: Profile.Gender,
    val userImageUrl: String,
)
