package com.cairosquad.evolvefit.ui.screen.register.content.component

import com.cairosquad.evolvefit.ui.component.ImagePicker
import com.cairosquad.evolvefit.ui.component.UiImageDisplayer

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage


@Composable
fun UserRegisterImage(
    image: UiImage,
    isImagePickerOpen: Boolean,
    onImagePickerDismiss: () -> Unit,
    onImagePickerClick: () -> Unit,
    onImageRetrieved: (UiImage) -> Unit,
    modifier: Modifier = Modifier,
    text: String? = null,
    isEditScreen: Boolean = false,
    defaultSize: Dp = 100.dp

) {
    var localImage by remember { mutableStateOf(image) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
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
                    image = image,
                    contentDescription = "Profile picture",
                    defaultImageSize = defaultSize,
                )
            }

        if (text.isNullOrEmpty().not()) {
            Text(
                text = text,
                style = Theme.textStyle.label.mediumMedium14,
                color = Theme.color.surfaces.onSurfaceVariant,
                modifier = Modifier.padding(top=8.dp,bottom = 32.dp),
            )
        }
    }
}