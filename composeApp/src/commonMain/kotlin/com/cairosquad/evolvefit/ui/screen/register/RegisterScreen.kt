package com.cairosquad.evolvefit.ui.screen.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.cairosquad.evolvefit.ui.screen.register.content.RegisterScreenContent
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.viewmodel.register.RegisterEffect
import com.cairosquad.evolvefit.viewmodel.register.RegisterViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RegisterScreen(
    navigateToApp: () -> Unit,
    navigateBack: () -> Unit,
    viewModel: RegisterViewModel = koinViewModel()
) {

    val state by viewModel.screenState.collectAsState()

    ObserveAsEffect(viewModel.effect) { effect ->
        when (effect) {
            RegisterEffect.NavigateBack -> navigateBack()
            RegisterEffect.NavigateToHome -> navigateToApp()
        }
    }

    RegisterScreenContent(
        state = state,
        listener = viewModel
    )
}
