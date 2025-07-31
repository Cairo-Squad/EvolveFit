package com.cairosquad.evolvefit.ui.screen.onBoarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme

@Composable
fun OnboardingScreen(
    navigateToLogin: () -> Unit,
    navigateToRegister: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Welcome to EvolveFit", color = Theme.color.surfaces.onSurface)
        Text("Get Fit Don't Quit", color = Theme.color.surfaces.onSurface)

        Button(
            onClick = navigateToLogin
        ){
            Text("go to Login")
        }
        Button(
            onClick = navigateToRegister
        ){
            Text("go to Register")
        }
    }
}