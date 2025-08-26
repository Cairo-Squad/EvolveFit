package com.cairosquad.evolvefit.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.appbar.ActionIconButton
import com.cairosquad.evolvefit.design_system.composables.InputField
import com.cairosquad.evolvefit.design_system.theme.Theme
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
            .padding(horizontal = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = Theme.textStyle.label.smallRegular12.copy(
                    color = Theme.color.surfaces.onSurfaceVariant
                ),
                modifier = Modifier.weight(1f)
            )
            if (trailingIcon != null) {
                ActionIconButton(
                    modifier = Modifier
                        .padding(start = 8.dp),
                    icon = painterResource(trailingIcon),
                    contentDescription = null,
                    tint = Theme.color.surfaces.onSurfaceVariant,
                    onClick = { onClick?.invoke() }
                )
            }
        }

        InputField(
            value = value,
            onValueChange = onValueChange,
            placeholder = placeholder,
            readOnly = readOnly,
            trailingIcon = null,
            onTrailingIconClick = onClick,
            onClick = if (readOnly) onClick else null,
            modifier = Modifier.fillMaxWidth().offset(x = (-10).dp),
            verticalPadding = 8.dp
        )

        if (isDividerVisible) {
            HorizontalDivider(
                thickness = 1.dp,
                color = Theme.color.surfaces.outlineVariant
            )
        }
    }
}
