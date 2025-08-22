package com.cairosquad.evolvefit.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.im_img
import io.github.vinceglb.filekit.PlatformFile
import org.jetbrains.compose.resources.painterResource

@Composable
fun UiImageDisplayer(
    image: UiImage?,
    contentDescription: String,
    defaultImageSize: Dp = 32.dp,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
) {
    when (image) {
        is UiImage.ImageFile -> {
            FileImageDisplayer(
                file = image.platformFile,
                contentDescription = contentDescription,
                defaultImageSize =defaultImageSize,
                contentScale = contentScale,
            )
        }

        is UiImage.ImageUrl -> {
            NetworkImage(
                modifier = Modifier.size(defaultImageSize),
                defaultImage = painterResource(Res.drawable.im_img),
                loadingPlaceHolder = painterResource(Res.drawable.im_img),
                model = image.url,
                contentDescription = contentDescription,
                placeholderImageSize = DpSize(defaultImageSize, defaultImageSize),
                contentScale = contentScale,
            )
        }

        is UiImage.ImageResource -> {
            Image(
                painter = painterResource(image.resourceId),
                contentDescription = contentDescription,
                contentScale = contentScale,
                modifier = Modifier.size(defaultImageSize)
            )
        }

        null -> {}
    }
}

@Composable
private fun FileImageDisplayer(
    file: PlatformFile,
    contentDescription: String,
    defaultImageSize: Dp,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
) {
    AsyncImage(
        model = file,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier.size(defaultImageSize)
    )
}