package com.cairosquad.evolvefit.viewmodel.onboarding.models

import io.github.vinceglb.filekit.PlatformFile
import org.jetbrains.compose.resources.DrawableResource

sealed interface UiImage {
    data class ImageUrl(val url: String) : UiImage

    data class ImageResource(val resourceId: DrawableResource) : UiImage

    data class ImageFile(val platformFile: PlatformFile) : UiImage

}