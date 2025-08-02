package com.cairosquad.evolvefit.viewmodel.register

sealed class RegisterEffect(){
    object NavigateToHome: RegisterEffect()
    object NavigateToNextScreen: RegisterEffect()
    object NavigateBack:RegisterEffect()
}