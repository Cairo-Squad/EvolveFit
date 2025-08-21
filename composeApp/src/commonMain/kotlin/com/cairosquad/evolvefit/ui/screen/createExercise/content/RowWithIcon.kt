package com.cairosquad.evolvefit.ui.screen.createExercise.content

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RowWithIcon(
    modifier: Modifier = Modifier,
    text: String,
    isIconClicked: Boolean = false,
    textColor: Color = Theme.color.surfaces.onSurfaceVariant,
    iconTint: Color = Theme.color.surfaces.onSurfaceVariant,
    background: Color = Theme.color.surfaces.surfaceContainer,
    onIconClicked: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .clickable(onClick = onIconClicked)
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
            color = textColor
        )
        Spacer(modifier = Modifier.weight(1f))
        AnimatedContent(
            targetState = isIconClicked,
            transitionSpec = {
                fadeIn() with fadeOut()
            }
        ) { target ->
            val iconPainter = if (target) {
                painterResource(Res.drawable.ic_arrow_up)
            } else {
                painterResource(Res.drawable.ic_arrow_down)
            }

            Icon(
                painter = iconPainter,
                contentDescription = stringResource(Res.string.arrow),
                tint = iconTint
            )
        }
    }
}

@Preview
@Composable
private fun RowWithIconPreview() {
    RowWithIcon(
        text = "Choose focus area",
        isIconClicked = true
    )
}