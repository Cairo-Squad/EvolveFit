package com.cairosquad.evolvefit.ui.screen.editProfile.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.BottomSheet
import com.cairosquad.evolvefit.design_system.component.CheckboxItem
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.ui.screen.register.content.RegisterHeader
import com.cairosquad.evolvefit.viewmodel.register.RegisterScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.choose_gender
import evolvefit.composeapp.generated.resources.confirm
import evolvefit.composeapp.generated.resources.female
import evolvefit.composeapp.generated.resources.male
import evolvefit.composeapp.generated.resources.select_gender_description
import evolvefit.composeapp.generated.resources.select_gender_title
import evolvefit.composeapp.generated.resources.save
import org.jetbrains.compose.resources.stringResource

@Composable
fun GenderBottomSheet(
    selectedGender: Profile.Gender,
    isGenderBottomSheetOpen: Boolean=true,
    onGenderBottomSheetDismiss: () -> Unit,
    onGenderChange: (Profile.Gender) -> Unit,
    modifier: Modifier = Modifier
) {
    BottomSheet(
        isVisible = isGenderBottomSheetOpen,
        onDismiss = onGenderBottomSheetDismiss,
        modifier = modifier.fillMaxWidth()
    ) {
        GenderBottomSheetContent(
            selectedGender = selectedGender,
            onGenderChange = onGenderChange,
            onGenderBottomSheetDismiss = onGenderBottomSheetDismiss,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun GenderBottomSheetContent(
    selectedGender: Profile.Gender,
    onGenderChange: (Profile.Gender) -> Unit,
    onGenderBottomSheetDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    var tempGender by remember { mutableStateOf(selectedGender) }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        RegisterHeader(
            modifier = Modifier.padding(bottom = 16.dp),
            title = stringResource(Res.string.choose_gender),
            titleStyle = Theme.textStyle.label.mediumMedium16
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Profile.Gender.entries.forEach { gender ->
                val genderText = when (gender) {
                    Profile.Gender.MALE -> stringResource(Res.string.male)
                    Profile.Gender.FEMALE -> stringResource(Res.string.female)
                }

                CheckboxItem(
                    text = genderText,
                    isChecked = tempGender == gender,
                    onCheckedChange = { tempGender = gender }
                )
            }
        }

        PrimaryButton(
            text = stringResource(Res.string.confirm),
            onClick = {
                onGenderChange(tempGender)
                onGenderBottomSheetDismiss()
            }
        )
    }
}
