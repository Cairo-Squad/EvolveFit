package com.cairosquad.evolvefit.ui.screen.register.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.InputField
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.component.DateBottomSheet
import com.cairosquad.evolvefit.ui.component.UserProfileImage
import com.cairosquad.evolvefit.ui.screen.register.content.component.UserRegisterImage
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
import com.cairosquad.evolvefit.viewmodel.register.RegisterInteractionListener
import com.cairosquad.evolvefit.viewmodel.register.RegisterScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.date_of_birth
import evolvefit.composeapp.generated.resources.enter_your_email
import evolvefit.composeapp.generated.resources.enter_your_name
import evolvefit.composeapp.generated.resources.ic_date
import evolvefit.composeapp.generated.resources.ic_end_arrow
import evolvefit.composeapp.generated.resources.ic_error
import evolvefit.composeapp.generated.resources.ic_lock
import evolvefit.composeapp.generated.resources.ic_mail
import evolvefit.composeapp.generated.resources.ic_profile
import evolvefit.composeapp.generated.resources.ic_visibility_off
import evolvefit.composeapp.generated.resources.ic_visibility_on
import evolvefit.composeapp.generated.resources.password
import evolvefit.composeapp.generated.resources.upload_image
import evolvefit.composeapp.generated.resources.user_profile
import evolvefit.composeapp.generated.resources.user_profile_description
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun RegisterScreenContentUserNamePasswordDateOfBirth(
    state: RegisterScreenState,
    listener: RegisterInteractionListener,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp),
    ) {
        UserProfileStep(
            image = state.image,
            state = state,
            maxDate = "2023-08-06",
            dateOfBirth = state.dateOfBirthInput,
            userName = state.userNameInput,
            userEmail = state.userEmailInput,
            userPassword = state.userPasswordInput,
            isImagePickerOpen = state.isImagePickerOpen,
            isPasswordVisible = state.isPasswordVisible,
            onImagePickerClick = listener::onImagePickerClick,
            onImagePickerDismiss = listener::onImagePickerDismiss,
            onImageRetrieved = listener::onImageRetrieved,
            onUserNameChange = listener::onUserNameChange,
            onUserEmailChange = listener::onUserEmailChange,
            onUserPasswordChange = listener::onUserPasswordChange,
            onPasswordVisibilityClick = listener::onPasswordVisibilityClick,
            onDateOfBirthChange = listener::onDateOfBirthChange,
        )
    }
}


@Composable
private fun UserProfileStep(
    image: UiImage,
    state: RegisterScreenState,
    isImagePickerOpen: Boolean,
    userName: String,
    userEmail: String,
    userPassword: String,
    isPasswordVisible: Boolean,
    maxDate: String,
    dateOfBirth: String,
    onImagePickerDismiss: () -> Unit,
    onImagePickerClick: () -> Unit,
    onImageRetrieved: (UiImage) -> Unit,
    onUserNameChange: (String) -> Unit,
    onUserEmailChange: (String) -> Unit,
    onUserPasswordChange: (String) -> Unit,
    onPasswordVisibilityClick: () -> Unit,
    onDateOfBirthChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
    ) {
        UserProfileStepHeader(
            modifier = Modifier
                .padding(bottom = 24.dp)
        )
        UserProfileImage(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            image = state.image,
            isImagePickerOpen = isImagePickerOpen,
            onImagePickerDismiss = onImagePickerDismiss,
            onImagePickerClick = onImagePickerClick,
            onImageRetrieved = { uiImage ->
                when (uiImage) {
                    is UiImage.ImageFile -> {
                        onImageRetrieved(uiImage)
                    }

                    is UiImage.ImageUrl -> {
                    }

                    else -> Unit
                }
            },
            isEditScreen = false,
            defaultSize = if (image is UiImage.ImageResource) 32.dp else 100.dp,
            text = stringResource(Res.string.upload_image)
        )

        UserProfileForm(
            userName = userName,
            state = state,
            onUserNameChange = onUserNameChange,
            userEmail = userEmail,
            onUserEmailChange = onUserEmailChange,
            userPassword = userPassword,
            onUserPasswordChange = onUserPasswordChange,
            isPasswordVisible = isPasswordVisible,
            onPasswordVisibilityClick = onPasswordVisibilityClick,
            dateOfBirth = dateOfBirth,
            maxDate = maxDate,
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
    state: RegisterScreenState,
    onUserNameChange: (String) -> Unit,
    userEmail: String,
    onUserEmailChange: (String) -> Unit,
    userPassword: String,
    onUserPasswordChange: (String) -> Unit,
    isPasswordVisible: Boolean,
    onPasswordVisibilityClick: () -> Unit,
    maxDate: String,
    dateOfBirth: String,
    onDateOfBirthChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isDatePickerBottomSheetOpen by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        InputField(
            modifier = Modifier.fillMaxWidth(),
            value = userName,
            onValueChange = onUserNameChange,
            placeholder = stringResource(Res.string.enter_your_name),
            leadingIcon = Res.drawable.ic_profile,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            verticalPadding = 15.5.dp
        )

        InputField(
            modifier = Modifier.fillMaxWidth(),
            value = userEmail,
            onValueChange = onUserEmailChange,
            placeholder = stringResource(Res.string.enter_your_email),
            leadingIcon = Res.drawable.ic_mail,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            isErrorMessageShown = state.emailError != null,
            error = state.emailError?.let { stringResource(it) } ?: "",
            isError = state.emailError != null,
            verticalPadding = 15.5.dp
        )

        InputField(
            modifier = Modifier.fillMaxWidth(),
            value = dateOfBirth,
            onValueChange = onDateOfBirthChange,
            placeholder = stringResource(Res.string.date_of_birth),
            leadingIcon = Res.drawable.ic_date,
            trailingIcon = Res.drawable.ic_end_arrow,
            readOnly = true,
            onClick = {
                isDatePickerBottomSheetOpen = true
            },
            verticalPadding = 15.5.dp
        )

        InputField(
            modifier = Modifier.fillMaxWidth(),
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
            isErrorMessageShown = state.passwordError != null,
            error = state.passwordError?.let { stringResource(it) } ?: "",
            isError = state.passwordError != null,
            verticalPadding = 15.5.dp
        )
        state.generalError?.let { errorRes ->
            Row(
                modifier = Modifier.padding(top = 7.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier.size(16.dp).padding(end=4.dp),
                    painter = painterResource(Res.drawable.ic_error),
                    contentDescription = "error",
                    tint = Theme.color.system.error
                )
                Text(
                    text = stringResource(errorRes),
                    color = Theme.color.system.error,
                    style = Theme.textStyle.label.smallRegular12,
                )
            }
        }
    }

    DateBottomSheet(
        maxDate = maxDate,
        dateOfBirth = dateOfBirth,
        onDateChange = onDateOfBirthChange,
        isDatePickerBottomSheetOpen = isDatePickerBottomSheetOpen,
        onDatePickerDismiss = {
            isDatePickerBottomSheetOpen = false
        },
        modifier = Modifier
    )
}