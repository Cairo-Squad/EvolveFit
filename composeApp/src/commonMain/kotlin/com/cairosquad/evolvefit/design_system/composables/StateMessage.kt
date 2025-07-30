package com.cairosquad.evolvefit.design_system.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.im_no_internet
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun StateMessage(
    image: DrawableResource,
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(width = 180.dp, height = 150.dp)
                .padding(bottom = 16.dp),
            imageVector = vectorResource(image),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = title,
            color = Theme.color.surfaces.onSurface,
            style = Theme.textStyle.title.mediumMedium16
        )
        Text(
            text = description,
            color = Theme.color.surfaces.onSurfaceVariant,
            style = Theme.textStyle.label.smallRegular12,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun StateMessagePreview() {
    AppTheme {
        StateMessage(
            image = Res.drawable.im_no_internet,
            title = "Error Title",
            description = "This is a detailed description of the error."
        )
    }
}

