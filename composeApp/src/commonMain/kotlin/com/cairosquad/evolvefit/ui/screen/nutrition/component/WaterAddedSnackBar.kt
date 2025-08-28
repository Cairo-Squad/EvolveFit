package com.cairosquad.evolvefit.ui.screen.nutrition.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cairosquad.evolvefit.design_system.component.SnackBar
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.water_added_successfully
import org.jetbrains.compose.resources.stringResource

@Composable
fun WaterAddedSnackBar(
    isVisible: Boolean,
    modifier: Modifier = Modifier
) {
    SnackBar(
        modifier = modifier,
        text = stringResource(Res.string.water_added_successfully),
        isVisible = isVisible,
        addNavBarPadding = false
    )
}
