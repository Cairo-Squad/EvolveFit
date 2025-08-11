package com.cairosquad.evolvefit.design_system.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_default_image
import evolvefit.composeapp.generated.resources.placeholder_image
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NetworkImage(
    model: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    defaultImage: Painter = painterResource(Res.drawable.ic_default_image),
    loadingPlaceHolder: Painter = painterResource(Res.drawable.ic_default_image),
    placeholderImageSize: DpSize? = null,
) {
    if (model.isNotBlank()) {
        AsyncImage(
            modifier = modifier,
            contentScale = contentScale,
            model = model,
            contentDescription = contentDescription,
            placeholder = loadingPlaceHolder,
            error = defaultImage
        )
    } else {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Theme.color.surfaces.surfaceContainer),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier
                    .then(
                        placeholderImageSize
                            ?.let { Modifier.size(it) }
                            ?: Modifier.fillMaxSize()
                    ),
                painter = defaultImage,
                contentDescription =
                    stringResource(Res.string.placeholder_image) + " :$contentDescription",
                tint = Theme.color.surfaces.onSurfaceVariant
            )
        }
    }
}

@Composable
@Preview
fun Preview() {
    AppTheme(
        isDarkTheme = true
    ) {
        NetworkImage(
            modifier = Modifier
                .size(92.dp, 72.dp)
                .clip(RoundedCornerShape(8.dp)),
            model = "",
            contentDescription = "Preview"
        )
    }
}