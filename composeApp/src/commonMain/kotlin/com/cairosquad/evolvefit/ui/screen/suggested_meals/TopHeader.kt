package com.cairosquad.evolvefit.ui.screen.suggested_meals

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_back
import evolvefit.composeapp.generated.resources.suggested_meals
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

@Composable
fun TopHeader(
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = vectorResource(Res.drawable.ic_back),
                contentDescription = "Back",
                tint = Theme.color.surfaces.onSurface
            )
        }
        Text(
            text = stringResource(Res.string.suggested_meals),
            style = Theme.textStyle.title.largeBold16,
            color = Theme.color.surfaces.onSurface,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}