package com.cairosquad.evolvefit.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_play
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun WorkoutCard(
    title: String,
    duration: String,
    focusArea: String,
    model: String,
    modifier: Modifier = Modifier,
) {
    val descriptionTextColor =
        if (model.isNotBlank() || model.isNotEmpty())
            Theme.color.surfaces.textColor
        else
            Theme.color.surfaces.onSurfaceContainer

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(140.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        NetworkImage(
            modifier = Modifier.fillMaxSize(),
            model = model,
            contentDescription = "Workout Image",
            placeholderImageSize = DpSize(92.dp, 72.dp),
        )
        Row(
            modifier = Modifier
                .padding(bottom = 12.dp, start = 12.dp, end = 12.dp)
                .height(50.dp)
                .fillMaxWidth().align(Alignment.BottomCenter),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(2.dp)
                    .clip(CircleShape)
                    .background(Theme.color.brand.primary)
            )
            Column(
                modifier = Modifier.weight(1f).padding(
                    start = 12.dp,
                ), verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    style = Theme.textStyle.title.largeBold16,
                    color = Theme.color.brand.primary,
                    maxLines = 1,
                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        duration,
                        style = Theme.textStyle.label.smallRegular12,
                        color = descriptionTextColor, maxLines = 1,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                    )
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .fillMaxHeight()
                            .width(1.dp)
                            .background(descriptionTextColor)
                    )
                    Text(
                        focusArea,
                        style = Theme.textStyle.label.smallRegular12,
                        color = descriptionTextColor,
                        maxLines = 1,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                    )
                }
            }

            Box(
                modifier = Modifier
                    .padding(end = 4.dp)
                    .clip(CircleShape)
                    .background(Theme.color.surfaces.onSurfaceAt2),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.padding(10.dp),
                    tint = Theme.color.brand.primary,
                    painter = painterResource(Res.drawable.ic_play),
                    contentDescription = "Play Button"
                )
            }
        }
    }
}

@Preview
@Composable
fun WorkoutCardPrev() {
    AppTheme(isDarkTheme = true) {
        WorkoutCard(
            title = "Jumping Jacks",
            duration = "30 sec",
            focusArea = "Full Body",
            model = "",
        )
    }
}