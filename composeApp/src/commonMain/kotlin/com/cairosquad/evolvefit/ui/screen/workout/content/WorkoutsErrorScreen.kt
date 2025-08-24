package com.cairosquad.evolvefit.ui.screen.workout.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.component.StateMessage
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_no_internet_light
import evolvefit.composeapp.generated.resources.im_no_internet
import evolvefit.composeapp.generated.resources.internet_is_not_available
import evolvefit.composeapp.generated.resources.please_make_sure_you_are_connected_to_the_internet_and_try_again
import evolvefit.composeapp.generated.resources.try_again_button
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun WorkoutsErrorScreen(
    message: String? = null,
    onRetry: () -> Unit
) {
    val noInternetIcon = if (Theme.isDark) {
        Res.drawable.im_no_internet
    } else {
        Res.drawable.ic_no_internet_light
    }
    Box(modifier = Modifier.fillMaxSize()) {
        StateMessage(
            image = painterResource(noInternetIcon),
            title = message ?: stringResource(Res.string.internet_is_not_available),
            description = stringResource(Res.string.please_make_sure_you_are_connected_to_the_internet_and_try_again),
            modifier = Modifier.align(Alignment.Center)
        )
        PrimaryButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 24.dp),
            text = stringResource(Res.string.try_again_button),
            enabledTextColor = Theme.color.brand.onPrimary,
            textStyle = Theme.textStyle.body.mediumMedium14,
            onClick = onRetry
        )
    }
}
