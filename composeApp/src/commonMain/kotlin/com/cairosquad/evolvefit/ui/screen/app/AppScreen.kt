package com.cairosquad.evolvefit.ui.screen.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.cairosquad.evolvefit.ui.screen.home.HomeScreen
import com.cairosquad.evolvefit.ui.screen.nutrition.NutritionScreen
import com.cairosquad.evolvefit.ui.screen.workout.WorkoutScreen

@Composable
fun AppScreen(

) {
    var selectedScreenIndex by rememberSaveable { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
    ){
        Box(
            modifier = Modifier.weight(1f)
        ) {
            when (selectedScreenIndex) {
                0 -> HomeScreen()
                1 -> NutritionScreen()
                2 -> WorkoutScreen()
            }
        }
        TempNavigationBar(
            selectedScreenIndex = selectedScreenIndex,
            onItemSelected = { selectedScreenIndex = it }
        )
    }
}

@Composable
private fun TempNavigationBar(
    selectedScreenIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        Button(
            onClick = { onItemSelected(0) }
        ){
            Text("home")
        }
        Button(
            onClick = { onItemSelected(1) }
        ){
            Text("Nutrition")
        }
        Button(
            onClick = { onItemSelected(2) }
        ){
            Text("Workouts")
        }
    }
}
