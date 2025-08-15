package com.cairosquad.evolvefit.ui.screen.workoutDetails.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme

@Composable
 fun WorkoutDetailsText(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    titleColor: Color = Theme.color.surfaces.onSurface,
    descriptionColor: Color = Theme.color.surfaces.onSurfaceVariant,
    titleStyle: TextStyle = Theme.textStyle.headline.mediumMedium18,
    descriptionStyle: TextStyle = Theme.textStyle.label.smallRegular14
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            color = titleColor,
            style = titleStyle,
        )
        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = description,
            color = descriptionColor,
            style = descriptionStyle,
        )
    }
}