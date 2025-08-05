package com.cairosquad.evolvefit.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
import io.github.vinceglb.filekit.dialogs.FileKitMode
import io.github.vinceglb.filekit.dialogs.FileKitType
import io.github.vinceglb.filekit.dialogs.compose.rememberFilePickerLauncher

@Composable
fun ImagePicker(
    onImageRetrieved: (UiImage) -> Unit,
    onImagePickerDismiss: () -> Unit
) {

    val launcher = rememberFilePickerLauncher(
        mode = FileKitMode.Single,
        type = FileKitType.Image,

        ) { file ->
        file?.let {
            onImageRetrieved(
                UiImage.ImageFile(file)
            )
        } ?: onImagePickerDismiss()
    }

    LaunchedEffect(true) {
        launcher.launch()
    }
}