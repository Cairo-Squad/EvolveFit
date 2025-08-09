package com.cairosquad.evolvefit.ui.screen.report.componant

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_water_drop
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun WaterCard(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Theme.color.surfaces.surfaceContainer)
            .padding(horizontal = 12.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HeaderSection()
        WaterMeter(
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun HeaderSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            modifier = Modifier.size(32.dp)
                .background(color = Theme.color.surfaces.surfaceVariant, shape = CircleShape)
                .padding(8.dp),
            painter = painterResource(Res.drawable.ic_water_drop),
            contentDescription = "Water drop icon",
            colorFilter = ColorFilter.tint(Theme.color.system.info)
        )
        Text(
            modifier = Modifier.weight(1f),
            text = "Water",
            style = Theme.textStyle.title.largeBold14,
            color = Theme.color.surfaces.onSurfaceContainer
        )
    }
}

@Preview
@Composable
private fun WaterCardPreview() {
    AppTheme(isDarkTheme = true) {
        WaterCard(
            modifier = Modifier
                .width(200.dp).height(214.dp)
        )
    }
}

@Preview
@Composable
private fun WaterCardLongPreview() {
    AppTheme(isDarkTheme = true) {
        WaterCard(
            modifier = Modifier
                .width(180.dp).height(262.dp)
        )
    }
}