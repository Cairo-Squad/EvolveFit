package com.cairosquad.evolvefit.ui.screen.splash

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_app_logo
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource

@Composable
fun SplashScreen(
    onFinished: () -> Unit,
    splashDuration: Long = 3000L
) {
    var startAnimation by remember { mutableStateOf(false) }
    var endAnimation by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = when {
            !startAnimation -> 0f
            endAnimation -> 0.8f
            else -> 1f
        },
        animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing),
        label = "Logo Scale"
    )

    val alpha by animateFloatAsState(
        targetValue = when {
            !startAnimation -> 0f
            endAnimation -> 0f
            else -> 1f
        },
        animationSpec = tween(durationMillis = 400),
        label = "Logo Alpha"
    )

    LaunchedEffect(Unit) {
        startAnimation = true
        delay(splashDuration - 600)
        endAnimation = true
        delay(600)
        onFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.color.brand.primary),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_app_logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(width = 112.dp, height = 100.dp)
                .scale(scale)
                .alpha(alpha),
            tint = Theme.color.brand.onPrimary
        )
    }
}
