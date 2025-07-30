package com.cairosquad.evolvefit.design_system.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.Res.drawable
import evolvefit.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ExerciseCard(title: String, time: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Image(
            painter = painterResource(drawable.ex), contentDescription = "",
        )
        Column() {
            Text(text = title)
            Text(text = time)
        }
    }
}

@Composable
@Preview
private fun ExerciseCardPreview() {
    ExerciseCard(title = "Bodyweight Squats", time = "30 sec")
}