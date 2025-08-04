package com.cairosquad.evolvefit.viewmodel.onboarding.models

import io.github.vinceglb.filekit.PlatformFile

sealed interface UiImage {
    data class ImageUrl(val url: String) : UiImage

    data class ImageResource(val resourceId: Int) : UiImage

    data class ImageFile(val platformFile: PlatformFile) : UiImage

}