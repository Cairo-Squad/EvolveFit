package com.cairosquad.evolvefit.ui.navigation.navBar

import androidx.navigation.NavController
import com.cairosquad.evolvefit.ui.navigation.NavBarRoute

fun NavController.navigateToNavBarRoute(route: NavBarRoute) {
    when (route){
        NavBarRoute.Home -> this.navigate(NavBarRoute.Home) {
            popUpTo(NavBarRoute.Home) { inclusive = true }
        }
        NavBarRoute.Nutrition -> this.navigate(NavBarRoute.Nutrition) {
            popUpTo(NavBarRoute.Nutrition) { inclusive = true }
        }
        NavBarRoute.Workout -> this.navigate(NavBarRoute.Workout) {
            popUpTo(NavBarRoute.Workout) { inclusive = true }
        }
        NavBarRoute.Report -> this.navigate(NavBarRoute.Report) {
            popUpTo(NavBarRoute.Report) { inclusive = true }
        }
        NavBarRoute.More -> this.navigate(NavBarRoute.More) {
            popUpTo(NavBarRoute.More) { inclusive = true }
        }
    }
}