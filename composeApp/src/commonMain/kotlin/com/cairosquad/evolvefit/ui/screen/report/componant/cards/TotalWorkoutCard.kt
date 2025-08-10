package com.cairosquad.evolvefit.ui.screen.report.componant.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.report.componant.CardHeaderSection
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_status_up
import evolvefit.composeapp.generated.resources.total_workouts
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TotalWorkoutCard(
    totalWorkoutCount: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .height(127.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Theme.color.surfaces.surfaceContainer)
            .padding(horizontal = 12.dp, vertical = 16.dp)
    ) {
        CardHeaderSection(
            painter = painterResource(Res.drawable.ic_status_up),
            tint = Theme.color.brand.primary,
            title = ""
        )
        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = "$totalWorkoutCount",
            color = Theme.color.surfaces.onSurfaceContainer,
            style = Theme.textStyle.display.largeBold24
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(Res.string.total_workouts),
            color = Theme.color.surfaces.onSurfaceVariant,
            style = Theme.textStyle.label.smallRegular12
        )
    }
}

@Preview
@Composable
fun TotalWorkoutCardPreview() {
    TotalWorkoutCard(
        modifier = Modifier
            .width(160.dp),
        totalWorkoutCount = 5
    )
}