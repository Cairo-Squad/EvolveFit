package com.cairosquad.evolvefit.ui.screen.register.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.composables.CheckboxItem
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.onBoarding.OnboardingHeader
import com.cairosquad.evolvefit.viewmodel.register.RegisterInteractionListener
import com.cairosquad.evolvefit.viewmodel.register.RegisterScreenState

@Composable
fun RegisterScreenContentChooseYourGoal(
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
            title = "What is your main goal?",
            description = "Select your goal to personalize your training program."
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CheckboxItem(
                text = "Lose weight",
                isChecked = state.isFemaleChecked,
                onCheckedChange = listener::onFemaleCheckedChange,
                description = "Get into your dream shape."
            )
            CheckboxItem(
                text = "Gain weight",
                isChecked = state.isMaleChecked,
                onCheckedChange = listener::onMaleCheckedChange,
                description = "Bulk up and build muscles."
            )
            CheckboxItem(
                text = "Stay in shape",
                isChecked = state.isMaleChecked,
                onCheckedChange = listener::onMaleCheckedChange,
                description = "Get a toned and leaner body."
            )

        }

    }
}