package com.cairosquad.evolvefit.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.upload_image
import org.jetbrains.compose.resources.stringResource

@Composable
fun UserProfileImage(
    image: UiImage,
    isImagePickerOpen: Boolean,
    onImagePickerDismiss: () -> Unit,
    onImagePickerClick: () -> Unit,
    onImageRetrieved: (UiImage) -> Unit,
    modifier: Modifier = Modifier,
    text : String ? = null
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .clip(CircleShape)
                .size(100.dp)
                .background(Theme.color.surfaces.surfaceContainer)
                .clickable(onClick = onImagePickerClick),
            contentAlignment = Alignment.Center
        ) {
            if (isImagePickerOpen) {
                ImagePicker(
                    onImageRetrieved = onImageRetrieved,
                    onImagePickerDismiss = onImagePickerDismiss
                )
            }

            UiImageDisplayer(
                image = image,
                contentDescription = "Profile picture", // TODO
                defaultImageSize = 32.dp
            )
        }
        if(!text.isNullOrEmpty()) {
            Text(
                text = text,
                style = Theme.textStyle.label.mediumMedium14,
                color = Theme.color.surfaces.onSurfaceVariant
            )
        }
    }
}