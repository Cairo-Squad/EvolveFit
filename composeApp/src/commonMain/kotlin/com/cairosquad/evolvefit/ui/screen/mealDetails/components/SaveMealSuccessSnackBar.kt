package com.cairosquad.evolvefit.ui.screen.mealDetails.components

import androidx.compose.runtime.Composable
import com.cairosquad.evolvefit.design_system.component.SnackBar
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_save_full
import evolvefit.composeapp.generated.resources.ic_save_tick
import evolvefit.composeapp.generated.resources.meal_added_to_your_favourites
import evolvefit.composeapp.generated.resources.meal_type_snacks
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SaveMealSuccessSnackBar(isVisible : Boolean){
    SnackBar(
        text =  stringResource(Res.string.meal_added_to_your_favourites) ,
        isVisible = isVisible,
        icon = painterResource(Res.drawable.ic_save_tick),
        iconTint = Theme.color.brand.primary

    )
}