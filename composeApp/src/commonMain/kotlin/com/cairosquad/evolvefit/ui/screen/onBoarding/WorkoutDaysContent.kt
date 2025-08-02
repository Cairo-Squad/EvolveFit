package com.cairosquad.evolvefit.ui.screen.onBoarding

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.Chip
import com.cairosquad.evolvefit.design_system.composables.SelectableDayBox
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.collections.get

@Composable
fun WorkoutDaysContent(modifier: Modifier = Modifier) {
    val weekdays = remember {
        listOf(
            "Saturday",
            "Sunday",
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday"
        )
    }
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
    ) {
        OnboardingHeader(
            title = "Workout Days",
            description = "How often would you like to workout?"
        )

        LazyVerticalGrid(
            modifier = modifier.padding(top = 24.dp).fillMaxSize(),
            columns = GridCells.Adaptive(minSize = 101.33.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(weekdays) {
                SelectableDayBox(textDay = it, isSelected = true, onClick = {})
            }
        }
    }
}

@Preview
@Composable
fun WorkoutDaysContentPreview() {
    AppTheme(isDarkTheme = true) {
        WorkoutDaysContent()
    }
}