package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.ui.screen.onBoarding.OnBoardingViewModel
import com.cairosquad.evolvefit.viewmodel.login.LoginViewModel
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionViewModel
import com.cairosquad.evolvefit.viewmodel.register.RegisterViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::RegisterViewModel)
    viewModelOf(::OnBoardingViewModel)
    viewModelOf(::NutritionViewModel)
    //viewModelOf(::CreateWorkoutViewModel)
    viewModelOf(::LoginViewModel)
}