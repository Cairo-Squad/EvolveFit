package com.cairosquad.evolvefit.ui.screen.report.componant.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.report.componant.CardHeaderSection
import com.cairosquad.evolvefit.ui.screen.report.componant.animatedMeter.CaloriesMeter
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.calories
import evolvefit.composeapp.generated.resources.ic_fire
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CaloriesCard(
    expectedCalories: Int,
    takenCalories: Int,
    isAnimationStarted: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Theme.color.surfaces.surfaceContainer)
            .padding(horizontal = 12.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CardHeaderSection(
            painter = painterResource(Res.drawable.ic_fire),
            tint = Theme.color.system.success,
            title = stringResource(Res.string.calories)
        )
        CaloriesMeter(
            modifier = Modifier.weight(1f),
            expectedCalories = expectedCalories,
            takenCalories = takenCalories,
            isAnimationStarted = isAnimationStarted
        )
    }
}

@Preview
@Composable
private fun CaloriesCardPreview() {
    AppTheme(isDarkTheme = true) {
        CaloriesCard(
            modifier = Modifier
                .width(200.dp).height(214.dp),
            expectedCalories = 3600,
            takenCalories = 2000,
            isAnimationStarted = true
        )
    }
}

@Preview
@Composable
private fun CaloriesCardLongPreview() {
    AppTheme(isDarkTheme = true) {
        CaloriesCard(
            modifier = Modifier
                .width(180.dp).height(262.dp),
            expectedCalories = 3600,
            takenCalories = 2000,
            isAnimationStarted = false
        )
    }
}