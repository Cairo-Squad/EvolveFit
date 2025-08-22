package com.cairosquad.evolvefit.ui.screen.nutrition.content

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.StateMessage
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_no_meals_light
import evolvefit.composeapp.generated.resources.im_no_meals_recorded
import evolvefit.composeapp.generated.resources.no_meals_description
import evolvefit.composeapp.generated.resources.no_meals_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun EmptyMealHistory() {
    Box(contentAlignment = Alignment.Center) {
        val noMealsRecorded = if (isSystemInDarkTheme()) {
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



