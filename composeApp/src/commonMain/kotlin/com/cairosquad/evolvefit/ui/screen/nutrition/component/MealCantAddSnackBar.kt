package com.cairosquad.evolvefit.ui.screen.nutrition.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cairosquad.evolvefit.design_system.component.SnackBar
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_info
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
 fun MealCantAddSnackBar(
    state: NutritionScreenState,
    isVisible: Boolean,
    modifier: Modifier = Modifier
) {
    SnackBar(
        modifier = modifier,
        icon = painterResource(Res.drawable.ic_info),
        text = state.errorMessage?.let { stringResource(it) } ?: "",
        isVisible = isVisible
    )
}