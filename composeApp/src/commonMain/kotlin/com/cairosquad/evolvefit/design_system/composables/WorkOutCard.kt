package com.cairosquad.evolvefit.design_system.composables

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_image
import evolvefit.composeapp.generated.resources.ic_play
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun WorkOutCard(
    title: String,
    duration: String,
    category: String,
    modifier: Modifier = Modifier,
    imageRes: Painter = painterResource(Res.drawable.ic_image),
    onCardClick: () -> Unit = {},
) {
    Box(
        modifier = modifier.fillMaxWidth().height(140.dp).clip(RoundedCornerShape(8.dp))
            .clickable { onCardClick() }) {
//        Image(
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.Crop,
//            painter = imageRes,
//            contentDescription = "Workout Image"
//        )
        Box(
            modifier = Modifier.fillMaxSize() .background(Color(0xFF242424)),
            contentAlignment = Alignment.Center
        ) {
            Image(

                painter = imageRes,
                contentDescription = "Workout Image"
            )
        }

        Row(
            modifier = Modifier
                .padding(bottom = 12.dp, start = 12.dp, end = 12.dp)
                .height(50.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(2.dp)
                    .clip(CircleShape)
                    .background(Theme.color.brand.primary)
            ) {}
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
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .wrapContentHeight()
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        duration,
                        style = Theme.textStyle.label.smallRegular12,
                        color = Theme.color.surfaces.onSurfaceContainer
                    )
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .fillMaxHeight()
                            .width(1.dp)
                            .background(Theme.color.surfaces.onSurfaceContainer)
                    ) { }
                    Text(
                        category,
                        style = Theme.textStyle.label.smallRegular12,
                        color = Theme.color.surfaces.onSurfaceContainer
                    )
                }
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .padding(end = 4.dp)
                    .clip(CircleShape).background(Theme.color.surfaces.onSurfaceAt2)

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
fun WorkOutCardPrev() {
    AppTheme(isDarkTheme = true) {
        WorkOutCard(
            title = "Jumping Jacks",
            duration = "30 sec",
            category = "Full Body",
        )
    }
}