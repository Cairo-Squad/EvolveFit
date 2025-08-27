package com.cairosquad.evolvefit.ui.screen.workoutHistory.content

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cairosquad.evolvefit.ui.component.ErrorComponent
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.im_no_internet
import evolvefit.composeapp.generated.resources.internet_is_not_available
import evolvefit.composeapp.generated.resources.please_make_sure_you_are_connected_to_the_internet_and_try_again
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun WorkoutHistoryScreenErrorContent(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    ErrorComponent(
        onRetry = onRetry,
        icon = painterResource(Res.drawable.im_no_internet),
        title = stringResource(Res.string.internet_is_not_available),
        description = stringResource(Res.string.please_make_sure_you_are_connected_to_the_internet_and_try_again),
        modifier = modifier.fillMaxSize()
    )
}
