package com.cairosquad.evolvefit.ui.screen.nutrition.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_end_arrow
import evolvefit.composeapp.generated.resources.view_all
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SeeAll(
    onViewAllClick: () -> Unit,
    sectionTitle: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(
            modifier = Modifier.weight(1f),
            text = sectionTitle,
            style = Theme.textStyle.label.mediumMedium16,
            color = Theme.color.surfaces.onSurface
        )
        Text(
            modifier = Modifier.clickable(
                onClick = { onViewAllClick() }
            ),
            text = stringResource(Res.string.view_all),
            style = Theme.textStyle.body.mediumMedium14,
            color = Theme.color.surfaces.onSurfaceVariant
        )
        Icon(
            modifier = Modifier
                .clickable(onClick = { onViewAllClick() })
                .padding(start = 4.dp),
            painter = painterResource(Res.drawable.ic_end_arrow),
            contentDescription = null,
            tint = Color.Unspecified
        )
    }
}