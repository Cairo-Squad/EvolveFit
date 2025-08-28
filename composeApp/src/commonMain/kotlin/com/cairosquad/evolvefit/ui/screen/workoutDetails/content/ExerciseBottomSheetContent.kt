package com.cairosquad.evolvefit.ui.screen.workoutDetails.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.Chip
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.workoutDetails.content.component.ImageCarousel
import com.cairosquad.evolvefit.ui.util.ScreenSize
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.bullet_point
import evolvefit.composeapp.generated.resources.close
import evolvefit.composeapp.generated.resources.equipment
import evolvefit.composeapp.generated.resources.focus_area
import evolvefit.composeapp.generated.resources.instructions
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ExerciseBottomSheetContent(
    name: String,
    instructions: List<String>,
    images: List<String>,
    specificationString: String,
    specificationIcon: Painter,
    equipment: String,
    focusAreas: List<String>,
    onDismissBottomSheet: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .navigationBarsPadding()
                .padding(bottom = 80.dp)
        ) {
            Text(
                modifier = Modifier.padding(bottom = 12.dp),
                text = name,
                style = Theme.textStyle.title.largeBold16,
                color = Theme.color.surfaces.onSurface,
            )
            ImageCarousel(
                images = images,
                specificationString = specificationString,
                specificationIcon = specificationIcon,
            )
            if (instructions.isNotEmpty()) {
                Text(
                    modifier = Modifier.padding(top = 16.dp, bottom = 12.dp),
                    text = stringResource(Res.string.instructions),
                    style = Theme.textStyle.headline.largeBold18,
                    color = Theme.color.surfaces.onSurface,
                )
                instructions.forEach { instruction ->
                    Row(
                        modifier = Modifier.padding(bottom = 4.dp, start = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "•",
                            style = Theme.textStyle.label.smallRegular14,
                            color = Theme.color.surfaces.onSurfaceVariant,
                        )
                        Text(
                            text = instruction,
                            style = Theme.textStyle.label.smallRegular14,
                            color = Theme.color.surfaces.onSurfaceVariant,
                        )
                    }
                }
            }
            if (equipment.isNotBlank() && !equipment.equals("unknown", ignoreCase = true)) {
                Text(
                    modifier = Modifier.padding(top = 20.dp, bottom = 12.dp),
                    text = stringResource(Res.string.equipment),
                    style = Theme.textStyle.headline.largeBold18,
                    color = Theme.color.surfaces.onSurface,
                )
                Text(
                    modifier = Modifier.padding(bottom = 24.dp, start = 4.dp),
                    text = stringResource(Res.string.bullet_point, equipment),
                    style = Theme.textStyle.label.smallRegular14,
                    color = Theme.color.surfaces.onSurfaceVariant,
                )
            }
            if (focusAreas.isNotEmpty()) {
                Text(
                    modifier = Modifier.padding(bottom = 12.dp),
                    text = stringResource(Res.string.focus_area),
                    style = Theme.textStyle.headline.largeBold18,
                    color = Theme.color.surfaces.onSurface,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    focusAreas.forEach { area -> Chip(title = area) }
                }
            }
        }
        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
                .navigationBarsPadding(),
            text = stringResource(Res.string.close),
            onClick = onDismissBottomSheet,
            isEnabled = true
        )
    }
}