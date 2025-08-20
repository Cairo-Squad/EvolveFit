package com.cairosquad.evolvefit.ui.screen.favorites.componants

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
import evolvefit.composeapp.generated.resources.ic_save_full
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun FavoritesCard(
    title: String,
    subtitle: String,
    info: String,
    model: String,
    modifier: Modifier = Modifier,
    onSaveIconClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(172.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        NetworkImage(
            modifier = Modifier.fillMaxSize(),
            model = model,
            contentDescription = "Workout Image",
            placeholderImageSize = DpSize(92.dp, 72.dp),
        )
        Box(
            modifier = Modifier.fillMaxSize()
                .background(Theme.color.surfaces.onSurfaceAt3)
                .clip(RoundedCornerShape(8.dp))
            ,
        )
        Box(
            modifier = Modifier
                .padding(12.dp)
                .clip(CircleShape)
                .align(Alignment.TopEnd)
                .background(Theme.color.surfaces.onSurfaceAt3),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.padding(10.dp)
                    .clickable(onClick = onSaveIconClick)
                ,
                tint = Theme.color.surfaces.textColor,
                painter = painterResource(Res.drawable.ic_save_full),
                contentDescription = "save icon"
            )
        }
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
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp, top = 4.dp, bottom = 4.dp)
                    .fillMaxHeight()
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = title,
                    style = Theme.textStyle.title.largeBold16,
                    color = Theme.color.brand.primary
                )
                Row(
                    modifier = Modifier.padding(top = 8.dp)
                        .wrapContentHeight().weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        subtitle,
                        style = Theme.textStyle.label.smallRegular12,
                        color = Theme.color.surfaces.textColor
                    )
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .fillMaxHeight()
                            .width(1.dp)
                            .background(Theme.color.surfaces.textColor)
                    )
                    Text(
                        info,
                        style = Theme.textStyle.label.smallRegular12,
                        color = Theme.color.surfaces.textColor
                    )
                }
            }

        }
    }
}

@Preview
@Composable
fun WorkoutCardPrev() {
    AppTheme(isDarkTheme = true) {
        FavoritesCard(
            title = "Jumping Jacks",
            subtitle = "30 sec",
            info = "Full Body",
            model = "",
        )
    }
}