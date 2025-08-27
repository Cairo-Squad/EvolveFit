package com.cairosquad.evolvefit.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.component.StateMessage
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.try_again_button
import org.jetbrains.compose.resources.stringResource

@Composable
fun ErrorComponent(
    onRetry: () -> Unit,
    icon: Painter,
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier.fillMaxSize()) {
        StateMessage(
            image = icon,
            title = title,
            description = description,
            modifier = Modifier.align(Alignment.Center)
        )
        PrimaryButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 24.dp),
            text = stringResource(Res.string.try_again_button),
            enabledTextColor = Theme.color.brand.onPrimary,
            textStyle = Theme.textStyle.body.mediumMedium14,
            onClick = onRetry
        )
    }
}