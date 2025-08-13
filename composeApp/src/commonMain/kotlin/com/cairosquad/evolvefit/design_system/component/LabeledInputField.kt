package com.cairosquad.evolvefit.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.composables.InputField
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_arrow_down
import evolvefit.composeapp.generated.resources.ic_back
import evolvefit.composeapp.generated.resources.ic_pen

@Composable
fun LabeledInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    readOnly: Boolean = false,
    showEditIcon: Boolean = true,
    onClick: (() -> Unit)? = null
) {
    Column(
        modifier = modifier.background(Theme.color.surfaces.surfaceContainer)
    ) {
        Text(
            text = label,
            style = Theme.textStyle.label.smallRegular12.copy(
                color = Theme.color.surfaces.onSurfaceVariant
            ),
            modifier = Modifier.padding(bottom = 9.dp)
        )

        InputField(
            value = value,
            onValueChange = onValueChange,
            placeholder = placeholder,
            readOnly = readOnly,
            trailingIcon = if (showEditIcon) Res.drawable.ic_pen else null,
            onTrailingIconClick = onClick,
            onClick = onClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Composable
fun LabeledDropdownField(
    label: String,
    value: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.background(Theme.color.surfaces.surfaceContainer)
    ) {
        Text(
            text = label,
            style = Theme.textStyle.label.smallRegular12.copy(
                color = Theme.color.surfaces.onSurfaceVariant
            ),
            modifier = Modifier.padding(bottom = 9.dp)
        )

        InputField(
            value = value,
            onValueChange = { },
            readOnly = true,
            trailingIcon = Res.drawable.ic_arrow_down,
            onClick = onClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}