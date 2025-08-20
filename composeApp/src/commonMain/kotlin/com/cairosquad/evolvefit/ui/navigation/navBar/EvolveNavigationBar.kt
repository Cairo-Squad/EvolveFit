package com.cairosquad.evolvefit.ui.navigation.navBar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.cairosquad.evolvefit.design_system.component.NavigationBar
import com.cairosquad.evolvefit.design_system.component.NavigationBarItem
import com.cairosquad.evolvefit.ui.navigation.NavBarRoute
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
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun EvolveNavigationBar(
    currentRoute: NavBarRoute,
    onSelectNavBarRoute: (navBarRoute: NavBarRoute) -> Unit,
    modifier: Modifier = Modifier,
    isVisible: Boolean = true
){
    val navigationItemsRes = remember {
        listOf(
            NavigationItemRes(
                outlinedIconRes = Res.drawable.dashboard,
                filledIconRes = Res.drawable.dashboard_filled,
                labelRes = Res.string.dashboard,
                index = NavBarRoute.Home.index
            ),
            NavigationItemRes(
                outlinedIconRes = Res.drawable.nutrition,
                filledIconRes = Res.drawable.nutrition_filled,
                labelRes = Res.string.nutrition,
                index = NavBarRoute.Nutrition.index
            ),
            NavigationItemRes(
                outlinedIconRes = Res.drawable.workouts,
                filledIconRes = Res.drawable.workouts_filled,
                labelRes = Res.string.workouts,
                index = NavBarRoute.Workout.index
            ),
            NavigationItemRes(
                outlinedIconRes = Res.drawable.reports,
                filledIconRes = Res.drawable.reports_filled,
                labelRes = Res.string.reports,
                index = NavBarRoute.Report.index
            ),
            NavigationItemRes(
                outlinedIconRes = Res.drawable.more,
                filledIconRes = Res.drawable.more_filled,
                labelRes = Res.string.more,
                index = NavBarRoute.More.index
            )
        ).sortedBy { it.index }
    }

    AnimatedVisibility(
        visible = isVisible,
        modifier = modifier,
        enter = slideInVertically(animationSpec = tween(500), initialOffsetY = { it }),
        exit = slideOutVertically(animationSpec = tween(500), targetOffsetY = { it })
    ){
        NavigationBar(
            modifier = Modifier.navigationBarsPadding(),
            selectedItem = currentRoute.index,
            onItemClick = {index -> onSelectNavBarRoute(NavBarRoute.Companion.fromIndex(index)) },
            navigationItems = navigationItemsRes.map { it.toNavigationBarItem() }
        )
    }
}

private data class NavigationItemRes(
    val outlinedIconRes: DrawableResource,
    val filledIconRes: DrawableResource,
    val labelRes: StringResource,
    val index: Int
) {
    @Composable
    fun toNavigationBarItem(): NavigationBarItem {
        return NavigationBarItem(
            outlinedIcon = painterResource(outlinedIconRes),
            filledIcon = painterResource(filledIconRes),
            label = stringResource(labelRes)
        )
    }
}