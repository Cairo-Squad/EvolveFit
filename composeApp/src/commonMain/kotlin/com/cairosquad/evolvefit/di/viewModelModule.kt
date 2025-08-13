package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.viewmodel.community_workout.CommunityWorkoutViewModel
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseViewModel
import com.cairosquad.evolvefit.viewmodel.login.LoginViewModel
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionViewModel
import com.cairosquad.evolvefit.viewmodel.onboarding.OnBoardingViewModel
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutViewModel
import com.cairosquad.evolvefit.viewmodel.register.RegisterViewModel
import com.cairosquad.evolvefit.viewmodel.report.ReportViewModel
import com.cairosquad.evolvefit.viewmodel.workout.WorkoutViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::RegisterViewModel)
    viewModelOf(::OnBoardingViewModel)
    viewModelOf(::NutritionViewModel)
    viewModelOf(::WorkoutViewModel)
    viewModelOf(::CommunityWorkoutViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::CreateExerciseViewModel)
    viewModelOf(::ReportViewModel)
    viewModel { (workoutId: String) ->
        PlayWorkoutViewModel(workoutId, manageWorkoutUseCase = get())
    }
}