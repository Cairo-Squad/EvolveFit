package com.cairosquad.evolvefit.viewmodel.register

sealed class RegisterEffect(){
    object NavigateToHome: RegisterEffect()
    object NavigateBack: RegisterEffect()
}