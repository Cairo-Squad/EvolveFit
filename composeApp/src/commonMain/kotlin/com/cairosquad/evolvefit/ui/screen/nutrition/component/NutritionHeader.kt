package com.cairosquad.evolvefit.ui.screen.nutrition.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.nutrition
import org.jetbrains.compose.resources.stringResource

@Composable
fun NutritionHeader() {
    Row(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()
            .background(Theme.color.surfaces.surface),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.statusBars),
            text = stringResource(Res.string.nutrition),
            style = Theme.textStyle.title.largeBold16,
            color = Theme.color.surfaces.onSurface
        )
    }
}