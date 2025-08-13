package com.cairosquad.evolvefit.design_system.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.composables.InputField
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_arrow_down
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun LabeledInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isDividerVisible: Boolean,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    readOnly: Boolean = false,
    trailingIcon: DrawableResource? = null,
    onClick: (() -> Unit)? = null,
) {
    Column(
        modifier = modifier
            .background(Theme.color.surfaces.surfaceContainer)
            .padding(horizontal = 12.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = Theme.textStyle.label.smallRegular12.copy(
                    color = Theme.color.surfaces.onSurfaceVariant
                ),
                modifier = Modifier.weight(1f).padding(start = 3.dp)
            )
            if (trailingIcon != null) {
                AnimatedContent(
                    targetState = trailingIcon,
                    transitionSpec = {
                        scaleIn(animationSpec = tween(300)).togetherWith(
                            scaleOut(
                                animationSpec = tween(
                                    300
                                )
                            )
                        )
                    }
                ) {
                    Icon(
                        painter = painterResource(it),
                        contentDescription = null,
                        tint = Theme.color.surfaces.onSurfaceVariant,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .clickable { onClick?.invoke() }
                    )
                }
            }

        }

        InputField(
            value = value,
            onValueChange = onValueChange,
            placeholder = placeholder,
            readOnly = readOnly,
            trailingIcon = null,
            onTrailingIconClick = onClick,
            onClick = onClick,
            modifier = Modifier.fillMaxWidth()
        )
        if (isDividerVisible) {
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 16.dp),
                thickness = 1.dp,
                color = Theme.color.surfaces.outlineVariant
            )
        }
    }
}
