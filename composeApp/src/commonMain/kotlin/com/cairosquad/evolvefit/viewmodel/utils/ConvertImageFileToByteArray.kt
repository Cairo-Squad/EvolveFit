package com.cairosquad.evolvefit.viewmodel.utils

import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
import io.github.vinceglb.filekit.name
import io.github.vinceglb.filekit.readBytes

suspend fun UiImage.asByteArray(): Pair<ByteArray, String> {
    return when (this) {
        is UiImage.ImageFile -> {
            val bytes = platformFile.readBytes()
            val fileName = platformFile.name
            bytes to fileName
        }
        else -> throw IllegalArgumentException("Only ImageFile can be converted to ByteArray")
    }
}