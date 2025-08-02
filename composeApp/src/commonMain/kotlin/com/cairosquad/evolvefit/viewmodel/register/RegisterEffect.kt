package com.cairosquad.evolvefit.viewmodel.register

sealed class RegisterEffect(){
    object NavigateToHome: RegisterEffect()
    class NavigateToNextScreen: RegisterEffect()
    class NavigateBack:RegisterEffect()
}