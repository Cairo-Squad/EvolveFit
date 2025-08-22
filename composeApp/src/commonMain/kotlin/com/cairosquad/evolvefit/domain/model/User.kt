package com.cairosquad.evolvefit.domain.model

import com.cairosquad.evolvefit.domain.entity.Profile

data class User(
    val userName: String,
    val userGender: Profile.Gender,
    val userImageUrl: String,
)