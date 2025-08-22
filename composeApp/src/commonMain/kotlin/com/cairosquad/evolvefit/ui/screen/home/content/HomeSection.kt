package com.cairosquad.evolvefit.ui.screen.home.content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme


@Composable
fun HomeSection(
    title: String,
    visibilityKey: Boolean,
    modifier: Modifier = Modifier,
    isPaddedStart: Boolean = false,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(visibilityKey) {
        Column(
            modifier = modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = title,
                style = Theme.textStyle.label.mediumMedium16,
                color = Theme.color.surfaces.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .then(
                        if (isPaddedStart) {
                            Modifier
                                .padding(start = 16.dp)
                        } else {
                            Modifier
                        }
                    )
            )

            content()
        }
    }
}