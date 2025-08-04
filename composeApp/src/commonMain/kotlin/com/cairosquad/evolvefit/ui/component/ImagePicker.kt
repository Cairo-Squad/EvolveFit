package com.cairosquad.evolvefit.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage

@Composable
fun ImagePicker(
    onImageRetrieved: (UiImage) -> Unit,
) {
    val scope = rememberCoroutineScope()

}