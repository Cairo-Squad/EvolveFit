package com.cairosquad.evolvefit.ui.screen.playWorkout.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_question_mark
import org.jetbrains.compose.resources.painterResource


@Composable
fun ExerciseNameAndInfoIcon(
    exerciseName: String,
    onClickInfo: () -> Unit,
    modifier: Modifier = Modifier,
    iconSize: Dp = 24.dp,
    textStyle: TextStyle = Theme.textStyle.display.mediumMedium20,
    textColor: Color = Theme.color.surfaces.onSurfaceContainer
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .padding(end = 8.dp),
            text = exerciseName,
            style = textStyle,
            color = textColor,
        )
        Icon(
            modifier = Modifier
                .size(iconSize)
                .clip(CircleShape)
                .clickable(
                    onClick = onClickInfo
                ),
            painter = painterResource(Res.drawable.ic_question_mark),
            contentDescription = "Exercise Info",
            tint = Theme.color.surfaces.onSurfaceVariant,
        )
    }
}