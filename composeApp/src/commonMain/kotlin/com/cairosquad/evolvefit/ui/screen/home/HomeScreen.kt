package com.cairosquad.evolvefit.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.cairosquad.evolvefit.design_system.theme.Theme

@Composable
fun HomeScreen(

) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.color.surfaces.surface),
        contentAlignment = Alignment.Center
    ) {
        Text("this is the home screen", color = Theme.color.surfaces.onSurface)
    }
}