package com.cairosquad.evolvefit.viewmodel.home

import com.cairosquad.evolvefit.viewmodel.base.ErrorState

sealed interface HomeScreenEffect {
    data class ShowErrorSnackBar(val errorState: ErrorState) : HomeScreenEffect
    data class ShowSuccessSnackBar(val message: String) : HomeScreenEffect // TODO: use resources
}