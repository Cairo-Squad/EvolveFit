package com.cairosquad.evolvefit.ui.screen.mealsHistory.content.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.StateMessage
import com.cairosquad.evolvefit.design_system.component.appbar.ActionIconButton
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.meal_history.MealHistoryViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.back
import evolvefit.composeapp.generated.resources.ic_back
import evolvefit.composeapp.generated.resources.ic_no_meals_light
import evolvefit.composeapp.generated.resources.im_no_meals_recorded
import evolvefit.composeapp.generated.resources.meal_history
import evolvefit.composeapp.generated.resources.no_meals_description
import evolvefit.composeapp.generated.resources.no_meals_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MealsHistoryEmptyScreen(
    viewModel: MealHistoryViewModel
) {
    Column {
        CustomAppBar(
            title = stringResource(Res.string.meal_history),
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(horizontal = 16.dp),
            header = {
                ActionIconButton(
                    icon = painterResource(Res.drawable.ic_back),
                    contentDescription = stringResource(Res.string.back),
                    tint = Theme.color.surfaces.onSurface,
                    onClick = { viewModel.onNavigateBackClicked() }
                )
            }

        )
        Box(contentAlignment = Alignment.Center) {
            val noMealsRecorded = if (Theme.isDark) {
                Res.drawable.im_no_meals_recorded
            } else {
                Res.drawable.ic_no_meals_light
            }
            StateMessage(
                modifier = Modifier.padding(vertical = 16.dp),
                image = painterResource(noMealsRecorded),
                title = stringResource(Res.string.no_meals_title),
                description = stringResource(Res.string.no_meals_description)
            )
        }
    }
}