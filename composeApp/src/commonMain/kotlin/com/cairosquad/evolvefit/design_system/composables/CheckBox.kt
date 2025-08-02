package com.cairosquad.evolvefit.design_system.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_check_mark
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckboxItem(
    text: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    description: String? = null,
    style: CheckboxStyle = CheckboxStyle.Tick
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color = Theme.color.surfaces.surfaceContainer )
            .fillMaxWidth()
            .clickable{ onCheckedChange(!isChecked) }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier.
            clip(RoundedCornerShape(8.dp))

                . background(color = Theme.color.surfaces.surfaceContainer )

                .weight(1f)
        ) {
            Text(
                text = text,
                style = Theme.textStyle.body.mediumMedium14,
                color = Theme.color.surfaces.onSurface
            )

            description?.let { desc ->
                Text(
                    text = desc,
                    style = Theme.textStyle.label.smallRegular12,
                    color = Theme.color.surfaces.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        when (style) {
            CheckboxStyle.Tick -> {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(
                            color = if (isChecked) Theme.color.brand.primary else Color.Transparent,
                            shape = CircleShape
                        )
                        .border(
                            width = 2.dp,
                            color = if (isChecked) Theme.color.brand.primary else Theme.color.surfaces.outlineVariant,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (isChecked) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_check_mark),
                            contentDescription = null,
                            tint = Theme.color.surfaces.surface,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }

            CheckboxStyle.Switch -> {
                Switch(
                    checked = isChecked,
                    onCheckedChange = onCheckedChange,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Theme.color.surfaces.surface,
                        checkedTrackColor = Theme.color.brand.primary,
                        uncheckedThumbColor = Theme.color.surfaces.surface,
                        uncheckedTrackColor = Theme.color.surfaces.outlineVariant,
                        uncheckedBorderColor = Color.Transparent,
                        disabledCheckedThumbColor = Theme.color.surfaces.surface,
                        disabledUncheckedThumbColor = Theme.color.surfaces.surface
                    )
                )
            }
        }
    }
}
enum class CheckboxStyle {
    Tick,
    Switch
}