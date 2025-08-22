package com.cairosquad.evolvefit.ui.screen.report.content.componant

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.arrow_down
import evolvefit.composeapp.generated.resources.ic_arrow_down
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun WeekFilter(
    currentWeek: String,
    onMenuClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(Theme.color.surfaces.surfaceContainer)
            .border(
                color = Theme.color.surfaces.outlineVariant,
                shape = RoundedCornerShape(4.dp),
                width = 0.5.dp
            )
            .clickable(onClick = onMenuClick)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = currentWeek,
            style = Theme.textStyle.body.mediumMedium12,
            color = Theme.color.surfaces.onSurfaceVariant
        )
        Image(
            modifier = Modifier.size(12.dp),
            painter = painterResource(Res.drawable.arrow_down),
            contentDescription = stringResource(Res.string.ic_arrow_down),
            colorFilter = ColorFilter.tint(Theme.color.surfaces.onSurfaceVariant)
        )
    }
}

@Preview
@Composable
private fun WeekFilterPreview() {
    WeekFilter("This week",{})
}