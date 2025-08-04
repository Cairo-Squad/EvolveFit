package com.cairosquad.evolvefit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.ui.screen.onBoarding.userProfileStep.UserProfileStep
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
import io.github.vinceglb.filekit.coil.addPlatformFileSupport
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context)
            .components {
                addPlatformFileSupport()
            }
            .build()
    }
    AppTheme {
//        NavigationHost()
        var image by remember { mutableStateOf<UiImage>(UiImage.ImageUrl("")) }
        var isImagePickerOpen by remember { mutableStateOf(false) }
        UserProfileStep(
            image = image,
            isImagePickerOpen = isImagePickerOpen,
            onImagePickerClick = {
                isImagePickerOpen = true
            },
            onImageRetrieved = {
                image = it
                isImagePickerOpen = false
            }
        )
    }
}