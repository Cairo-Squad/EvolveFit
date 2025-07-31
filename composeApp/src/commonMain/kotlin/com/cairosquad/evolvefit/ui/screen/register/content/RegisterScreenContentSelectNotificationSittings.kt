package com.cairosquad.evolvefit.ui.screen.register.content

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.register.RegisterInteractionListener
import com.cairosquad.evolvefit.viewmodel.register.RegisterScreenState

@Composable
fun RegisterScreenContentSelectNotificationSittings(
    state: RegisterScreenState,
    listener: RegisterInteractionListener,
    modifier: Modifier = Modifier
){
    Text("RegisterScreenContentSelectNotificationSittings", color = Theme.color.surfaces.onSurface)
}