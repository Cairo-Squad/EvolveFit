package com.cairosquad.evolvefit.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage

@Composable
fun UiImageDisplayer(
    image: UiImage,
    isImagePickerOpen: Boolean,
    onImagePickerClick: () -> Unit,
    onImageRetrieved: (UiImage) -> Unit,
    modifier: Modifier = Modifier
) {

}