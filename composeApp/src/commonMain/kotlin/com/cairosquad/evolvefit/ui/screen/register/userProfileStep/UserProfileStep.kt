package com.cairosquad.evolvefit.ui.screen.register.userProfileStep

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.component.UserProfileImage
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.user_profile
import evolvefit.composeapp.generated.resources.user_profile_description
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun UserProfileStep(
    image: UiImage,
    isImagePickerOpen: Boolean,
    onImagePickerDismiss: () -> Unit,
    onImagePickerClick: () -> Unit,
    onImageRetrieved: (UiImage) -> Unit,
    userName: String,
    onUserNameChange: (String) -> Unit,
    userEmail: String,
    onUserEmailChange: (String) -> Unit,
    userPassword: String,
    onUserPasswordChange: (String) -> Unit,
    isPasswordVisible: Boolean,
    onPasswordVisibilityClick: () -> Unit,
    dateOfBirth: String,
    onDateOfBirthChange: (String) -> Unit, // TODO: change date handling?
    onStartNowClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(
                horizontal = 16.dp
            )
    ) {
        UserProfileStepHeader(
            modifier = Modifier
                .padding(bottom = 24.dp)

        )

        UserProfileImage(
            image = image,
            isImagePickerOpen = isImagePickerOpen,
            onImagePickerDismiss = onImagePickerDismiss,
            onImagePickerClick = onImagePickerClick,
            onImageRetrieved = onImageRetrieved,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(
                    bottom = 32.dp
                )
        )

//        UserProfileForm(
//
//        )
    }
}

@Composable
private fun UserProfileStepHeader(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(Res.string.user_profile),
            style = Theme.textStyle.headline.mediumMedium18,
            color = Theme.color.surfaces.onSurface,
            modifier = Modifier
                .padding(bottom = 8.dp)
        )

        Text(
            text = stringResource(Res.string.user_profile_description),
            style = Theme.textStyle.label.smallRegular14,
            color = Theme.color.surfaces.onSurfaceVariant
        )
    }
}

@Preview
@Composable
fun PreviewUserProfileStep() {
    AppTheme(
        isDarkTheme = true
    ) {
        UserProfileStep(
            image = UiImage.ImageUrl(""),
            isImagePickerOpen = false,
            onImagePickerClick = {},
            onImageRetrieved = {},
            onImagePickerDismiss = {},
            userName = "",
            onUserNameChange = {},
            userEmail = "",
            onUserEmailChange = {},
            userPassword = "",
            onUserPasswordChange = {},
            isPasswordVisible = false,
            onPasswordVisibilityClick = {},
            dateOfBirth = "",
            onDateOfBirthChange = {},
            onStartNowClick = {}
        )
    }
}