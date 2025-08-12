package com.cairosquad.evolvefit.ui.screen.report.componant

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.activity
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ActivityRow(
    onMenuClicked: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(Res.string.activity),
            color = Theme.color.surfaces.onSurface,
            style = Theme.textStyle.title.mediumMedium16
        )
        WeekFilter(
            currentWeek = "This Week",
            onMenuClick = onMenuClicked
        )
    }
}

@Preview
@Composable
private fun ActivityRowPreview() {
    ActivityRow(onMenuClicked = {})
}