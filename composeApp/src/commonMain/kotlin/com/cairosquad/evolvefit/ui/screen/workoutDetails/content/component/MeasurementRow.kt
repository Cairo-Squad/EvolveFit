package com.cairosquad.evolvefit.ui.screen.workoutDetails.content.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.workout_details.WorkoutDetailsScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_count
import evolvefit.composeapp.generated.resources.ic_time
import evolvefit.composeapp.generated.resources.seconds
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun MeasurementRow(
    specificationString: String,
    specificationIcon: Painter,
    modifier: Modifier = Modifier,
    iconTint: Color = Theme.color.surfaces.onSurfaceVariant,
    textColor: Color = Theme.color.surfaces.onSurfaceVariant,
    textStyle: TextStyle = Theme.textStyle.label.smallRegular12
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = specificationIcon,
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = specificationString,
            color = textColor,
            style = textStyle,
        )
    }
}