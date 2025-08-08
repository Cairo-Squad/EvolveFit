package com.cairosquad.evolvefit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.register.userProfileStep.UserProfileStep
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
import io.github.vinceglb.filekit.coil.addPlatformFileSupport
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context)
            .components {
                addPlatformFileSupport()
            }
            .build()
    }
    AppTheme {
//        NavigationHost()
        var image by remember { mutableStateOf<UiImage>(UiImage.ImageUrl("")) }
        var isImagePickerOpen by remember { mutableStateOf(false) }
        var userName by remember { mutableStateOf("") }
        var userEmail by remember { mutableStateOf("") }
        var userPassword by remember { mutableStateOf("") }
        var isPasswordVisible by remember { mutableStateOf(false) }
        var dateOfBirth by remember { mutableStateOf("2023-08-09") }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Theme.color.surfaces.surface)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            UserProfileStep(
                image = image,
                isImagePickerOpen = isImagePickerOpen,
                onImagePickerClick = {
                    isImagePickerOpen = true
                },
                onImagePickerDismiss = {
                    isImagePickerOpen = false
                },
                onImageRetrieved = {
                    image = it
                    isImagePickerOpen = false
                },
                userName = userName,
                onUserNameChange = {
                    userName = it
                },
                userEmail = userEmail,
                onUserEmailChange = {
                    userEmail = it
                },
                userPassword = userPassword,
                onUserPasswordChange = {
                    userPassword = it
                },
                isPasswordVisible = isPasswordVisible,
                onPasswordVisibilityClick = {
                    isPasswordVisible = !isPasswordVisible
                },
                dateOfBirth = dateOfBirth,
                onDateOfBirthChange = {
                    dateOfBirth = it
                },
            )
        }
    }
}