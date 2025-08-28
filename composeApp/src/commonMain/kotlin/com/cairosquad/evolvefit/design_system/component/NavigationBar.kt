package com.cairosquad.evolvefit.design_system.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.dashboard
import evolvefit.composeapp.generated.resources.dashboard_filled
import evolvefit.composeapp.generated.resources.more
import evolvefit.composeapp.generated.resources.more_filled
import evolvefit.composeapp.generated.resources.nutrition
import evolvefit.composeapp.generated.resources.nutrition_filled
import evolvefit.composeapp.generated.resources.reports
import evolvefit.composeapp.generated.resources.reports_filled
import evolvefit.composeapp.generated.resources.workouts
import evolvefit.composeapp.generated.resources.workouts_filled
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

data class NavigationBarItem(
    val outlinedIcon: Painter,
    val filledIcon: Painter,
    val label: String
)

@Composable
fun NavigationBar(
    onItemClick: (Int) -> Unit,
    navigationItems: List<NavigationBarItem>,
    modifier: Modifier = Modifier,
    selectedItem: Int = 0,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Theme.color.surfaces.surface)
            .windowInsetsPadding(WindowInsets.navigationBars)
            .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        navigationItems.forEachIndexed { index, navItem ->
            NavigationItem(
                modifier = Modifier.weight(1f),
                navItem = navItem,
                isSelected = selectedItem == index,
                onClick = { onItemClick(index) },
                isClickEnabled = selectedItem != index
            )
        }
    }
}

@Composable
private fun NavigationItem(
    navItem: NavigationBarItem,
    isSelected: Boolean,
    onClick: () -> Unit,
    isClickEnabled: Boolean = true,
    modifier: Modifier = Modifier,
) {
    val icon = if (isSelected) navItem.filledIcon else navItem.outlinedIcon
    val textColor by animateColorAsState(
        targetValue =
            if (isSelected) Theme.color.brand.primary
            else Theme.color.surfaces.onSurfaceVariant,
    )
    Column(
        modifier = modifier.clickable(onClick = onClick, enabled = isClickEnabled),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedContent(
            targetState = icon,
            transitionSpec = {
                (fadeIn(tween(450))).togetherWith(fadeOut(tween(450)))
            }
        ) {
            Image(
                painter = icon,
                contentDescription = navItem.label,
                modifier = Modifier.size(24.dp),
            )
        }
        Text(
            text = navItem.label,
            style = Theme.textStyle.body.smallRegular10,
            modifier = Modifier.padding(top = 4.dp),
            color = textColor
        )
    }
}

@Preview
@Composable
private fun NavigationBarPreview() {
    val selectedItem: MutableState<Int> = mutableIntStateOf(0)
    AppTheme(
        isDarkTheme = true
    ) {
        Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
            NavigationBar(
                selectedItem = selectedItem.value, onItemClick = {
                    selectedItem.value = it
                }, navigationItems = listOf(
                    NavigationBarItem(
                        outlinedIcon = painterResource(Res.drawable.dashboard),
                        filledIcon = painterResource(Res.drawable.dashboard_filled),
                        label = stringResource(Res.string.dashboard)
                    ),
                    NavigationBarItem(
                        outlinedIcon = painterResource(Res.drawable.nutrition),
                        filledIcon = painterResource(Res.drawable.nutrition_filled),
                        label = stringResource(Res.string.nutrition)
                    ),
                    NavigationBarItem(
                        outlinedIcon = painterResource(Res.drawable.workouts),
                        filledIcon = painterResource(Res.drawable.workouts_filled),
                        label = stringResource(Res.string.workouts)
                    ),
                    NavigationBarItem(
                        outlinedIcon = painterResource(Res.drawable.reports),
                        filledIcon = painterResource(Res.drawable.reports_filled),
                        label = stringResource(Res.string.reports)
                    ),
                    NavigationBarItem(
                        outlinedIcon = painterResource(Res.drawable.more),
                        filledIcon = painterResource(Res.drawable.more_filled),
                        label = stringResource(Res.string.more)
                    ),

                    ), modifier = Modifier.align(Alignment.Center)
            )
        }
    }

}