package com.cairosquad.evolvefit.ui.screen.register.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.SelectableDayBox
import com.cairosquad.evolvefit.viewmodel.register.RegisterInteractionListener
import com.cairosquad.evolvefit.viewmodel.register.RegisterScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.workout_days_description
import evolvefit.composeapp.generated.resources.workout_days_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun RegisterScreenContentSelectWorkoutDays(
    state: RegisterScreenState,
    listener: RegisterInteractionListener,
    modifier: Modifier = Modifier
) {
    val weekdays = remember { RegisterScreenState.WorkoutDay.entries.toList() }
    Column(
        modifier = modifier
            .padding(top = 16.dp)
            .fillMaxSize()
    ) {
        RegisterHeader(
            title = stringResource(Res.string.workout_days_title),
            description = stringResource(Res.string.workout_days_description)
        )

        LazyVerticalGrid(
            modifier = modifier
                .padding(horizontal = 16.dp)
                .padding(top = 24.dp)
                .fillMaxSize(),
            columns = GridCells.Adaptive(minSize = 101.33.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(weekdays) { day ->
                val isSelected = state.selectedWorkoutDays.contains(day)
                SelectableDayBox(
                    textDay = stringResource(day.resId),
                    isSelected = isSelected,
                    onClick = { listener.onWorkoutDaySelected(day) })
            }
        }
    }
}