package com.cairosquad.evolvefit.ui.screen.createExercise.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.arrow
import evolvefit.composeapp.generated.resources.ic_arrow_down
import evolvefit.composeapp.generated.resources.ic_arrow_up
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun RowWithIcon(
    modifier: Modifier = Modifier,
    text: String = "Select focus area",
    isIconClicked: Boolean = false,
    iconTint: Color = Theme.color.surfaces.onSurfaceVariant,
    background: Color = Theme.color.surfaces.surfaceContainer,
    onIconClicked: () -> Unit = {}
) {
    val arrowDirection = if (isIconClicked) {
        painterResource(Res.drawable.ic_arrow_down)

    } else {
        painterResource(Res.drawable.ic_arrow_up)
    }

    Row(
        modifier = modifier
            .background(
                color = background,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 12.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = Theme.textStyle.label.smallRegular12,
            color = Theme.color.surfaces.onSurfaceVariant
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            modifier = Modifier
                .clickable(onClick = onIconClicked),
            painter = arrowDirection,
            contentDescription = stringResource(Res.string.arrow),
            tint = iconTint,
        )
    }
}

@Preview
@Composable
private fun RowWithIconPreview() {
    RowWithIcon()
}