package com.cairosquad.evolvefit.ui.screen.more

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.cairosquad.evolvefit.design_system.component.PrimaryButton

@Composable
fun MoreScreen(
    navigateToEditProfile: () -> Unit
) {
    Column {

        PrimaryButton(
            text="enough",
            onClick = navigateToEditProfile
        )
    }
}
