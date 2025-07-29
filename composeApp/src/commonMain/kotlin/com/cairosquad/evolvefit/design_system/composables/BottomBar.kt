package com.cairosquad.evolvefit.design_system.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
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
import evolvefit.composeapp.generated.resources.ic_dashboard
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

data class BottomNavItem(
    val icon: Painter, val labelId: StringResource
)

@Composable
fun NavigationBar(
    onItemClick: (Int) -> Unit,
    navigationItems: List<BottomNavItem>,
    modifier: Modifier = Modifier,
    selectedItem: Int = 0,
) {
    Row(
        modifier = modifier.height(72.dp).background(Theme.color.surfaces.surface).fillMaxSize()
            .padding(
                start = 20.dp, end = 20.dp, top = 16.dp, bottom = 16.dp
            ).windowInsetsPadding(WindowInsets.navigationBars),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        navigationItems.forEachIndexed { index, navItem ->
            NavigationItem(
                navItem = navItem,
                isSelected = selectedItem == index,
                onClick = { onItemClick(index) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun NavigationItem(
    navItem: BottomNavItem,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val label = stringResource(navItem.labelId)
    val selectedItemColor by animateColorAsState(
        targetValue = if (isSelected) Theme.color.brand.primary else Theme.color.surfaces.onSurfaceVariant,
        label = "icon and text color animation"
    )
    Column(
        modifier = modifier.clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = navItem.icon,
            contentDescription = label,
            modifier = Modifier.size(24.dp),
            tint = selectedItemColor
        )
        Text(
            text = label,
            style = Theme.textStyle.body.smallRegular10,
            modifier = Modifier.padding(top = 4.dp),
            color = selectedItemColor
        )
    }
}