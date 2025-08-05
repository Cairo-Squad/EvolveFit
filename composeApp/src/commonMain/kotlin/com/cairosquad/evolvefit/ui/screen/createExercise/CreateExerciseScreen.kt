package com.cairosquad.evolvefit.ui.screen.createExercise

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme

@Composable
fun CreateExerciseScreen(
    navigateBack: () -> Unit,
    onExerciseCreationSuccess: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.color.surfaces.surface)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            modifier = Modifier.padding(12.dp),
            text = "Create Exercise Screen", color = Theme.color.surfaces.onSurface
        )

        Button(
            onClick = {
                onExerciseCreationSuccess()
                navigateBack()
            }
        ) {
            Text("finish creating the exercise")
        }

        Button(
            onClick = navigateBack
        ) {
            Text("Cancel creating an exercise")
        }
    }
}