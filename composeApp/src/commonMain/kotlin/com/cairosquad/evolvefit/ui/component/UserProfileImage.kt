package com.cairosquad.evolvefit.ui.component

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
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_camera
import org.jetbrains.compose.resources.painterResource

@Composable
fun UserProfileImage(
    image: UiImage,
    isImagePickerOpen: Boolean,
    onImagePickerDismiss: () -> Unit,
    onImagePickerClick: () -> Unit,
    onImageRetrieved: (UiImage) -> Unit,
    modifier: Modifier = Modifier,
    text: String? = null,
    isEditScreen: Boolean = false,

) {
    var localImage by remember { mutableStateOf(image) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .padding(bottom = 8.dp)
                .size(100.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .fillMaxSize()
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
                    defaultImageSize = 32.dp,

                )
            }
            if (isEditScreen) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Theme.color.surfaces.surface)
                        .border(
                            width = 1.dp,
                            color = Theme.color.surfaces.outlineVariant,
                            shape = CircleShape
                        )
                        .clickable { onImagePickerClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_camera),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = Theme.color.brand.primary
                    )
                }
            }
        }

        if (!text.isNullOrEmpty()) {
            Text(
                text = text,
                style = Theme.textStyle.label.mediumMedium14,
                color = Theme.color.surfaces.onSurfaceVariant,
                modifier=Modifier.padding(
                    bottom = 32.dp
                    ),
            )
        }

    }
}

