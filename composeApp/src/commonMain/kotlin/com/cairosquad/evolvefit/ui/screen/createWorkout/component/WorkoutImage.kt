package com.cairosquad.evolvefit.ui.screen.createWorkout.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.component.ImagePicker
import com.cairosquad.evolvefit.ui.component.UiImageDisplayer
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage

@Composable
fun WorkoutImage(
    image: UiImage,
    isImagePickerOpen: Boolean,
    onImagePickerDismiss: () -> Unit,
    onImagePickerClick: () -> Unit,
    onImageRetrieved: (UiImage) -> Unit,
    modifier: Modifier = Modifier,
    text: String? = null
) {
    var localImage by remember { mutableStateOf(image) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .background(Theme.color.surfaces.surfaceContainer)
                .clickable(onClick = onImagePickerClick),
            contentAlignment = Alignment.Center
        ) {
            if (isImagePickerOpen) {
                ImagePicker(
                    onImageRetrieved = { picked ->
                        localImage = picked
                        onImageRetrieved(picked)
                        onImagePickerDismiss()
                    },
                    onImagePickerDismiss = onImagePickerDismiss
                )
            }

            UiImageDisplayer(
                image = localImage,
                contentDescription = "Profile picture",
                defaultImageSize = 100.dp,
                modifier= Modifier.size(100.dp)
            )
        }
        if (!text.isNullOrEmpty()) {
            Text(
                text = text,
                style = Theme.textStyle.label.mediumMedium14,
                color = Theme.color.surfaces.onSurfaceVariant
            )
        }
    }
}