package com.cairosquad.evolvefit.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = label,
                style = Theme.textStyle.label.smallRegular12,
                color = Theme.color.surfaces.onSurfaceVariant
            )
            if (trailingIcon != null) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(trailingIcon),
                    contentDescription = null,
                    tint = Theme.color.surfaces.onSurfaceVariant
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
            modifier = Modifier.fillMaxWidth(),
            verticalPadding = 0.dp,
            horizontalPadding = 0.dp
        )

        if (isDividerVisible) {
            HorizontalDivider(
                modifier = Modifier.padding(top = 12.dp),
                thickness = 1.dp,
                color = Theme.color.surfaces.outlineVariant
            )
        }
    }
}
