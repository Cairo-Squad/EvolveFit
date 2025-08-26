package com.cairosquad.evolvefit.ui.screen.mealDetails.content.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.appbar.ActionIconButton
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.meal_details.MealDetailsScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.back
import evolvefit.composeapp.generated.resources.bookmark
import evolvefit.composeapp.generated.resources.ic_back
import evolvefit.composeapp.generated.resources.ic_bookmark_big
import evolvefit.composeapp.generated.resources.ic_bookmark_big_filled
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MealDetailsAppBar(
    state: MealDetailsScreenState,
    onBackClick: () -> Unit,
    onBookmarkClick: () -> Unit
) {
    CustomAppBar(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(horizontal = 16.dp),
        title = "",
        header = {
            ActionIconButton(
                icon = painterResource(Res.drawable.ic_back),
                contentDescription = stringResource(Res.string.back),
                tint = Theme.color.surfaces.textColor,
                onClick = onBackClick
            )
        },
        tail = {
            Icon(
                painter =
                    if (state.mealDetails.isFavouriteMeal)
                        painterResource(Res.drawable.ic_bookmark_big_filled)
                    else painterResource(Res.drawable.ic_bookmark_big),
                contentDescription = stringResource(Res.string.bookmark),
                tint = Theme.color.surfaces.textColor,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .clickable(onClick = onBookmarkClick)
                    .padding(8.dp)
            )

        }
    )
}