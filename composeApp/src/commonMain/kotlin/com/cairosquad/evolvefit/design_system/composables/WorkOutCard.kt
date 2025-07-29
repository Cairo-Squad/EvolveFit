package com.cairosquad.evolvefit.design_system.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_workout
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.works

@Composable
fun WorkOutCard(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            //  .width(328.dp)
            .height(140.dp)
            .clip(RoundedCornerShape(8.dp))

    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            painter = painterResource(Res.drawable.works),
            contentDescription = null
        )

        Row(
            modifier = Modifier.padding(bottom = 12.dp, start = 12.dp, end = 12.dp).fillMaxWidth()
                .height(50.dp)
                .align(Alignment.BottomCenter),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.fillMaxHeight().width(1.dp)
                    .clip(RoundedCornerShape(bottomStart = 8.dp, topStart = 8.dp))
                    .border(
                        width = 1.dp,
                        color = Theme.color.brand.primary,
                        shape = RoundedCornerShape(bottomStart = 8.dp, topStart = 8.dp)
                    )
                    .background(Theme.color.brand.primary)

            ) { }

            Column(modifier = Modifier.padding(start = 8.dp, top = 4.dp, bottom = 4.dp).fillMaxHeight()) {
                Text(
                    modifier = Modifier.weight(1f),
                   text =  "Jumping Jacks",
                    style = Theme.textStyle.title.largeBold16,
                    color = Theme.color.brand.primary
                )
                Row(
                    modifier = Modifier.padding(top = 8.dp).wrapContentHeight().weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "30 sec",
                        style = Theme.textStyle.label.smallRegular12,
                        color = Theme.color.surfaces.onSurfaceContainer
                    )
                    Column(
                        modifier = Modifier.padding(horizontal = 8.dp).fillMaxHeight()
                            .width(1.dp)
                            .background(Theme.color.surfaces.onSurfaceContainer)

                    ) { }
                    Text(
                        "Full Body",
                        style = Theme.textStyle.label.smallRegular12,
                        color = Theme.color.surfaces.onSurfaceContainer
                    )
                }
            }
        }

    }
}

@Preview
@Composable
fun WorkOutCardPrev() {
    AppTheme(isDarkTheme = true) {
        WorkOutCard()
    }
}
// https://www.pexels.com/photo/person-holding-barbell-841130/

//        AsyncImage(
//            model = "https://images.pexels.com/photos/841130/pexels-photo-841130.jpeg",
//            contentDescription = null
//        )