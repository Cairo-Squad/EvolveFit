package com.cairosquad.evolvefit.viewmodel.register

sealed class RegisterEffect {
    class NavigateToNextScreen: RegisterEffect()
    class NavigateBack:RegisterEffect()
}