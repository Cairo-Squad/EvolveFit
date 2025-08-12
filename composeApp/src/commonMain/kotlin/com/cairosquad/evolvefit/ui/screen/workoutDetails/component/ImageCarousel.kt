package com.cairosquad.evolvefit.ui.screen.workoutDetails.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_arrow_down
import evolvefit.composeapp.generated.resources.ic_time
import org.jetbrains.compose.resources.painterResource


@Composable
fun ImageCarousel(
    images: List<String>,
    modifier: Modifier = Modifier
) {
    var currentIndex by remember { mutableStateOf(0) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(400.dp)
            .background(Color.Black)
    ) {
        NetworkImage(
            model = images[currentIndex],
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
        )

        Icon(
            painter = painterResource(Res.drawable.ic_arrow_down),
            contentDescription = "Previous",
            tint = if (currentIndex == 0) Theme.color.surfaces.onSurfaceVariant else Theme.color.surfaces.textColor,
            modifier = Modifier.align(Alignment.CenterStart)
                .padding(horizontal = 8.dp)
                .background(Theme.color.surfaces.onSurfaceAt2, CircleShape)
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .size(16.dp)
                .clickable(
                    onClick = {
                        currentIndex = if (currentIndex > 0) currentIndex - 1 else images.size - 1
                    }
                )
        )

        Icon(
            painter = painterResource(Res.drawable.ic_arrow_down),
            contentDescription = "Next",
            tint = Theme.color.surfaces.textColor,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(horizontal = 8.dp)
                .background(Theme.color.surfaces.onSurfaceAt2, CircleShape)
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .size(16.dp)
                .clickable(
                    onClick = {
                        currentIndex = if (currentIndex < images.size - 1) currentIndex + 1 else 0
                    }
                )
        )

        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp)
                .background(
                    Theme.color.surfaces.onSurfaceAt2,
                    RoundedCornerShape(24.dp)
                )
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_time),
                contentDescription = "time icon",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}