package com.cairosquad.evolvefit.ui.screen.createExercise.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.CheckboxItem
import com.cairosquad.evolvefit.design_system.component.CheckboxStyle
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.add_duration
import evolvefit.composeapp.generated.resources.add_reps
import org.jetbrains.compose.resources.stringResource

@Composable
fun MeasurementTypeSelector(
    isDurationChecked: Boolean,
    isRepsChecked: Boolean,
    durationTitleColor: Color,
    repsTitleColor: Color,
    onMeasurementTypeSelected: (CreateExerciseState.MeasurementType) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CheckboxItem(
            modifier = Modifier.weight(1f),
            text = stringResource(Res.string.add_duration),
            titleColor = durationTitleColor,
            isChecked = isDurationChecked,
            onCheckedChange = {
                if (it) onMeasurementTypeSelected(CreateExerciseState.MeasurementType.DURATION)
            },
            style = CheckboxStyle.Tick
        )
        CheckboxItem(
            modifier = Modifier.weight(1f),
            text = stringResource(Res.string.add_reps),
            titleColor = repsTitleColor,
            isChecked = isRepsChecked,
            onCheckedChange = {
                if (it) onMeasurementTypeSelected(CreateExerciseState.MeasurementType.REPS)
            },
            style = CheckboxStyle.Tick
        )
    }
}
