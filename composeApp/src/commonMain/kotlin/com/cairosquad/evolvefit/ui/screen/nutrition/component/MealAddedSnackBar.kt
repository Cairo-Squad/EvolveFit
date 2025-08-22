package com.cairosquad.evolvefit.ui.screen.nutrition.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cairosquad.evolvefit.design_system.component.SnackBar
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.meal_added_snackbar
import org.jetbrains.compose.resources.stringResource

@Composable
fun MealAddedSnackBar(
    isVisible: Boolean,
    modifier: Modifier = Modifier
) {
    SnackBar(
        modifier = modifier,
        text = stringResource(Res.string.meal_added_snackbar),
        isVisible = isVisible
    )
}
