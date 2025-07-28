package com.cairosquad.evolvefit.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun Button(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Theme.color.brand.primary,
    borderColor: Color = Theme.color.brand.primary,
    text: String = "Get Started",
    textColor: Color = Theme.color.brand.onPrimary,
    textStyle: TextStyle = Theme.textStyle.body.mediumMedium14,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .border(1.dp, borderColor, shape = RoundedCornerShape(24.dp))
            .background(backgroundColor),
        horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            color = textColor,
            style = textStyle,
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 12.dp)
        )
    }
}

@Preview
@Composable
private fun ButtonPrev1() {
    Button(
        borderColor = Theme.color.brand.primary,
        backgroundColor = Theme.color.brand.primary,
        textColor = Theme.color.brand.onPrimary
    )
}

@Preview
@Composable
private fun ButtonPrev2() {
    Button(
        borderColor = Theme.color.surfaces.outlineVariant,
        backgroundColor = Theme.color.surfaces.outlineVariant,
        textColor = Theme.color.surfaces.outline
    )
}

@Preview
@Composable
private fun ButtonPrev3() {
    AppTheme(isDarkTheme = false) {
        Button(
            borderColor = Theme.color.brand.primary,
            backgroundColor = Color.Transparent,
            textColor = Theme.color.brand.onPrimary
        )
    }
}