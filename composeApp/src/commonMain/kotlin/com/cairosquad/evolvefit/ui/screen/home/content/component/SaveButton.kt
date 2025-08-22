package com.cairosquad.evolvefit.ui.screen.home.content.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_bookmark_big
import evolvefit.composeapp.generated.resources.ic_bookmark_big_filled
import evolvefit.composeapp.generated.resources.save
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SaveButton(
    isSaved: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .size(40.dp)
            .background(Theme.color.surfaces.onSurfaceAt3)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        AnimatedContent(
            targetState = isSaved,
            transitionSpec = {
                scaleIn(animationSpec = tween(300)).togetherWith(scaleOut(animationSpec = tween(300)))
            }
        ) {
            Icon(
                painter = painterResource(
                    if (it) {
                        Res.drawable.ic_bookmark_big_filled
                    } else {
                        Res.drawable.ic_bookmark_big
                    }
                ),
                contentDescription = stringResource(Res.string.save),
                tint = Theme.color.surfaces.textColor,
                modifier = Modifier
                    .size(20.dp)
            )
        }
    }
}
