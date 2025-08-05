package com.cairosquad.evolvefit.ui.screen.register.userProfileStep

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.composables.InputField
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.component.UserProfileImage
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.enter_your_email
import evolvefit.composeapp.generated.resources.enter_your_name
import evolvefit.composeapp.generated.resources.ic_date
import evolvefit.composeapp.generated.resources.ic_lock
import evolvefit.composeapp.generated.resources.ic_mail
import evolvefit.composeapp.generated.resources.ic_profile
import evolvefit.composeapp.generated.resources.ic_visibility_off
import evolvefit.composeapp.generated.resources.ic_visibility_on
import evolvefit.composeapp.generated.resources.password
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

        UserProfileForm(
            userName = userName,
            onUserNameChange = onUserNameChange,
            userEmail = userEmail,
            onUserEmailChange = onUserEmailChange,
            userPassword = userPassword,
            onUserPasswordChange = onUserPasswordChange,
            isPasswordVisible = isPasswordVisible,
            onPasswordVisibilityClick = onPasswordVisibilityClick,
            dateOfBirth = dateOfBirth,
            onDateOfBirthChange = onDateOfBirthChange,
        )
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

@Composable
private fun UserProfileForm(
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
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        InputField(
            value = userName,
            onValueChange = onUserNameChange,
            placeholder = stringResource(Res.string.enter_your_name),
            leadingIcon = Res.drawable.ic_profile,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
        )

        InputField(
            value = userEmail,
            onValueChange = onUserEmailChange,
            placeholder = stringResource(Res.string.enter_your_email),
            leadingIcon = Res.drawable.ic_mail,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
        )

        // TODO: date of birth composable

        InputField(
            value = userPassword,
            onValueChange = onUserPasswordChange,
            placeholder = stringResource(Res.string.password),
            leadingIcon = Res.drawable.ic_lock,
            trailingIcon = if (isPasswordVisible) Res.drawable.ic_visibility_on else Res.drawable.ic_visibility_off,
            onTrailingIconClick = onPasswordVisibilityClick,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            isPasswordField = !isPasswordVisible,
            modifier = Modifier
                .fillMaxWidth(),
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
        )
    }
}