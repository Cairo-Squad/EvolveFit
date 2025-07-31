package com.cairosquad.evolvefit.ui.screen.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.cairosquad.evolvefit.ui.screen.register.content.RegisterScreenContent
import com.cairosquad.evolvefit.viewmodel.register.RegisterViewModel

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = RegisterViewModel()
) {

    val state by viewModel.state.collectAsState()

    RegisterScreenContent(
        state = state,
        listener = viewModel
    )

}
