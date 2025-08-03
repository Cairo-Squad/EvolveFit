package com.cairosquad.evolvefit.ui.screen.register.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.composables.CheckboxItem
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.onBoarding.OnboardingHeader
import com.cairosquad.evolvefit.viewmodel.register.RegisterInteractionListener
import com.cairosquad.evolvefit.viewmodel.register.RegisterScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.female
import evolvefit.composeapp.generated.resources.male
import evolvefit.composeapp.generated.resources.select_gender_description
import evolvefit.composeapp.generated.resources.select_gender_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun RegisterScreenContentSelectGender(
    state: RegisterScreenState,
    listener: RegisterInteractionListener,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().background(Theme.color.surfaces.surface)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        OnboardingHeader(
            title = stringResource(Res.string.select_gender_title),
            description = stringResource(Res.string.select_gender_description)
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            RegisterScreenState.Gender.entries.forEach { gender ->
                CheckboxItem(
                    text = when (gender) {
                        RegisterScreenState.Gender.Male -> stringResource(Res.string.male)
                        RegisterScreenState.Gender.Female -> stringResource(Res.string.female)
                    },
                    isChecked = state.selectedGender == gender,
                    onCheckedChange = { listener.onGenderClicked(gender) },
                )
            }

        }

    }
}
