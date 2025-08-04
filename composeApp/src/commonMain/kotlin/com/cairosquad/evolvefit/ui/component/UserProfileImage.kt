package com.cairosquad.evolvefit.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage

@Composable
fun UserProfileImage(
    image: UiImage,
    isImagePickerOpen: Boolean,
    onImagePickerDismiss: () -> Unit,
    onImagePickerClick: () -> Unit,
    onImageRetrieved: (UiImage) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(100.dp)
                .background(Theme.color.surfaces.surfaceContainer)
                .clickable(onClick = onImagePickerClick),
            contentAlignment = Alignment.Center
        ) {
            UiImageDisplayer(
                image = image,
                contentDescription = "Profile picture", // TODO
                isImagePickerOpen = isImagePickerOpen,
                onImagePickerDismiss = onImagePickerDismiss,
                onImageRetrieved = onImageRetrieved,
                defaultImageSize = 32.dp
            )
        }
    }
}