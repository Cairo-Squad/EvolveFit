package com.cairosquad.evolvefit.ui.screen.report.content.componant

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.calories
import evolvefit.composeapp.generated.resources.ic_fire
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CardHeaderSection(
    painter: Painter,
    tint: Color,
    title: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            modifier = Modifier.size(32.dp)
                .background(color = Theme.color.surfaces.surfaceVariant, shape = CircleShape)
                .padding(8.dp),
            painter = painter,
            contentDescription = "Water drop icon",
            colorFilter = ColorFilter.tint(tint)
        )
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            style = Theme.textStyle.title.largeBold14,
            color = Theme.color.surfaces.onSurfaceContainer
        )
    }
}

@Preview
@Composable
fun CardHeaderSectionPreview() {
    CardHeaderSection(
        painter = painterResource(Res.drawable.ic_fire),
        tint = Theme.color.system.success,
        title = stringResource(Res.string.calories)
    )
}