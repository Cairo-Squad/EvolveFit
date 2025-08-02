package com.cairosquad.evolvefit.ui.screen.onBoarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun YourToolsContent(modifier: Modifier= Modifier){
    Column(modifier = modifier.fillMaxSize()) {
        OnboardingHeader(
            title = "Your Tools",
            description = "Select with tools you have, and we’ll tailor your custom plan to reflect this."
        )

    }
}

@Preview
@Composable
fun YourToolsContentPreview(){
    YourToolsContent()
}