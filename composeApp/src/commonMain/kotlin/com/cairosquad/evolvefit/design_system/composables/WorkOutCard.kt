package com.cairosquad.evolvefit.design_system.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_workout
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage

@Composable
fun WorkOutCard(modifier: Modifier = Modifier) {
    Box(modifier = modifier.width(328.dp)
        .height(140.dp)
        .clip(RoundedCornerShape(8.dp))) {
//        AsyncImage(
//            model = "https://images.pexels.com/photos/841130/pexels-photo-841130.jpeg",
//            contentDescription = null
//        )
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            painter = painterResource(Res.drawable.ic_workout),
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun WorkOutCardPrev() {
    WorkOutCard()
}
// https://www.pexels.com/photo/person-holding-barbell-841130/