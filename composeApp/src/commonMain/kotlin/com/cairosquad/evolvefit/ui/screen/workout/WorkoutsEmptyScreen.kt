package com.cairosquad.evolvefit.ui.screen.workout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.StateMessage
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.workout.content.WorkoutFocusAreaFilter
import com.cairosquad.evolvefit.viewmodel.workout.WorkoutInteractionListener
import com.cairosquad.evolvefit.viewmodel.workout.WorkoutScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_no_meals_light
import evolvefit.composeapp.generated.resources.im_no_meals_recorded
import evolvefit.composeapp.generated.resources.no_workouts_subtitle
import evolvefit.composeapp.generated.resources.no_workouts_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun WorkoutsEmptyScreen(
    lazyListState: LazyListState,
    state: WorkoutScreenState,
    listener: WorkoutInteractionListener
) {
    Column {
        WorkoutFocusAreaFilter(
            lazyListState = lazyListState,
            focusArea = WorkoutScreenState.FocusAreaUiState.entries,
            selectedFocusArea = state.selectedFocusArea,
            onSelectFocusArea = listener::onFocusAreaSelected
        )
        Box(contentAlignment = Alignment.Center) {
            val noMealsRecorded = if (Theme.isDark) {
                Res.drawable.im_no_meals_recorded
            } else {
                Res.drawable.ic_no_meals_light
            }
            StateMessage(
                modifier = Modifier.padding(vertical = 16.dp),
                image = painterResource(noMealsRecorded),
                title = stringResource(Res.string.no_workouts_title),
                description = stringResource(Res.string.no_workouts_subtitle)
            )
        }
    }
}