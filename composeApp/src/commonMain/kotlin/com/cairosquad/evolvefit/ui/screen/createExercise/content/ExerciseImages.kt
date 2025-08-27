package com.cairosquad.evolvefit.ui.screen.createExercise.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.component.UiImageDisplayer
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.exercise_image
import evolvefit.composeapp.generated.resources.upload_image
import org.jetbrains.compose.resources.stringResource

@Composable
fun ExerciseImages(
    startImage: UiImage?,
    endImage: UiImage?,
    uploadExercisesImg1: Painter?,
    uploadExercisesImg2: Painter?,
    onStartImageClicked: () -> Unit,
    onEndImageClicked: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, top = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        ImageBox(
            image = endImage,
            defaultImage = uploadExercisesImg2,
            onClick = onEndImageClicked,
            modifier = Modifier
                .rotate(-5f)
                .offset(x = (-15).dp, y = (-18).dp)
        )

        ImageBox(
            image = startImage,
            defaultImage = uploadExercisesImg1,
            onClick = onStartImageClicked
        )
    }
}


@Composable
fun ImageBox(
    image: UiImage?,
    defaultImage: Painter?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(100.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Theme.color.surfaces.surfaceContainer)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (image != null) {
            UiImageDisplayer(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxSize(),
                image = image,
                contentDescription = stringResource(Res.string.exercise_image),
                contentScale = ContentScale.Crop,
                defaultImageSize = 100.dp
            )
        } else {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = defaultImage!!,
                contentDescription = stringResource(Res.string.upload_image)
            )
        }
    }
}