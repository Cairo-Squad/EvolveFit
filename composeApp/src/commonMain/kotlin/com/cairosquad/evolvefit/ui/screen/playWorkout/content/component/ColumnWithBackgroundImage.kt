package com.cairosquad.evolvefit.ui.screen.playWorkout.content.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import com.cairosquad.evolvefit.ui.util.ScreenSize
import evolvefit.composeapp.generated.resources.Onboarding
import evolvefit.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource


@Composable
fun ColumnWithBackgroundImage(
    model: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    contentPadding: PaddingValues = PaddingValues(),
    backgroundBlurRadius: Dp = 32.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    Box(modifier = modifier){
        NetworkImage(
            modifier = Modifier
                .matchParentSize()
                .blur(backgroundBlurRadius),
            model = model,
            contentDescription = contentDescription,
            defaultImage = painterResource(Res.drawable.Onboarding),
            loadingPlaceHolder = painterResource(Res.drawable.Onboarding)
        )
        Column(
            modifier = Modifier
                .matchParentSize()
                .padding(contentPadding),
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
            content = content
        )
    }
}