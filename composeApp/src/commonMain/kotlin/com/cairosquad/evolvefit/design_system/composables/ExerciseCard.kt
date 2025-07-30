package com.cairosquad.evolvefit.design_system.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.im_default_image
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ExerciseCard(title: String, time: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth().height(68.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painter = painterResource(Res.drawable.im_default_image),
            contentDescription = "Exercise Image",
            Modifier
                .width(88.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Column(
            modifier = Modifier.weight(1f)

                .padding(start = 12.dp, top = 12.5.dp, bottom = 12.5.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            Text(text = time, fontSize = 12.sp, fontWeight = FontWeight.Normal)
        }
    }
}

@Preview
@Composable
private fun ExerciseCardPreview() {
    ExerciseCard(title = "Bodyweight Squats", time = "30 sec")
}