package com.cairosquad.evolvefit.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.app.AppScreen
import com.cairosquad.evolvefit.ui.screen.login.LoginScreen
import com.cairosquad.evolvefit.ui.screen.onBoarding.OnboardingScreen
import com.cairosquad.evolvefit.ui.screen.register.RegisterScreen
import com.cairosquad.evolvefit.ui.screen.splash.SplashScreen

@Composable
fun NavigationHost() {

    val navController = rememberNavController()

    NavHost(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.color.surfaces.surface),
        navController = navController,
        startDestination = SplashRoute
    ) {
        composable<SplashRoute> {
            SplashScreen(
                onFinished = {
                    navController.navigate(OnboardingRoute) {
                        popUpTo(SplashRoute) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable<OnboardingRoute> {
            OnboardingScreen(
                navigateToLogin = { navController.navigate(LoginRoute) },
                navigateToRegister = { navController.navigate(RegisterRoute) },
            )
        }
        composable<OnboardingRoute> {
            OnboardingScreen(
                navigateToLogin = { navController.navigate(LoginRoute) },
                navigateToRegister = { navController.navigate(RegisterRoute) },
            )
        }

        composable<LoginRoute> {
            LoginScreen(
                navigateToApp = {
                    navController.navigate(AppRoute) {
                        popUpTo(OnboardingRoute) {
                            inclusive = true
                        }
                    }
                },
            )
        }

        composable<RegisterRoute> {
            RegisterScreen(
                navigateToApp = {
                    navController.navigate(AppRoute) {
                        popUpTo(OnboardingRoute) {
                            inclusive = true
                        }
                    }
                },
                navigateBack = { navController.popBackStack() }
            )
        }

        composable<AppRoute> {
            AppScreen()
        }

    }
}