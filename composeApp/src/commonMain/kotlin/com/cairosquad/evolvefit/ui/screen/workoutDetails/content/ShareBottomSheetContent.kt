package com.cairosquad.evolvefit.ui.screen.workoutDetails.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.util.noRippleClickable
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.copy_link
import evolvefit.composeapp.generated.resources.ic_copy
import evolvefit.composeapp.generated.resources.ic_instagram
import evolvefit.composeapp.generated.resources.ic_massenger
import evolvefit.composeapp.generated.resources.ic_telegram
import evolvefit.composeapp.generated.resources.ic_wattsapp
import evolvefit.composeapp.generated.resources.ic_x
import evolvefit.composeapp.generated.resources.instagram
import evolvefit.composeapp.generated.resources.messenger
import evolvefit.composeapp.generated.resources.share_to
import evolvefit.composeapp.generated.resources.telegram
import evolvefit.composeapp.generated.resources.whatsapp
import evolvefit.composeapp.generated.resources.x_platform
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ShareBottomSheetContent(
    onShareOptionClick: (String) -> Unit,
    onCopyLinkClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Theme.color.surfaces.surface)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(Res.string.share_to),
            color = Theme.color.surfaces.onSurface,
            style = Theme.textStyle.label.mediumMedium16,
        )

        ShareOptionsRow(onShareOptionClick)

        HorizontalDivider(Modifier, 1.dp, Theme.color.surfaces.outlineVariant)

        ShareActionRow(
            icon = painterResource(Res.drawable.ic_copy),
            label = stringResource(Res.string.copy_link),
            onClick = onCopyLinkClick
        )
    }
}

@Composable
fun ShareOptionsRow(onShareOptionClick: (String) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ShareOptionItem(
            icon = painterResource(Res.drawable.ic_massenger),
            label = stringResource(Res.string.messenger),
            onClick = { onShareOptionClick("Messenger") }
        )
        ShareOptionItem(
            icon = painterResource(Res.drawable.ic_wattsapp),
            label = stringResource(Res.string.whatsapp),
            onClick = { onShareOptionClick("WhatsApp") }
        )
        ShareOptionItem(
            icon = painterResource(Res.drawable.ic_telegram),
            label = stringResource(Res.string.telegram),
            onClick = { onShareOptionClick("Telegram") }
        )
        ShareOptionItem(
            icon = painterResource(Res.drawable.ic_instagram),
            label = stringResource(Res.string.instagram),
            onClick = { onShareOptionClick("Instagram") }
        )
        ShareOptionItem(
            icon = painterResource(Res.drawable.ic_x),
            label = stringResource(Res.string.x_platform),
            onClick = { onShareOptionClick("X") }
        )
    }
}

@Composable
fun ShareOptionItem(
    icon: Painter,
    label: String,
    onClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .noRippleClickable(onClick = onClick),
        verticalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(shape = CircleShape)
                .clickable(onClick = onClick)
                .background(Theme.color.surfaces.surfaceContainer, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = icon,
                contentDescription = label,
                tint = Theme.color.surfaces.onSurfaceContainer,
                modifier = Modifier.size(24.dp)
            )
        }
        Text(
            text = label,
            color = Theme.color.surfaces.onSurfaceContainer,
            style = Theme.textStyle.label.smallRegular12
        )
    }
}

@Composable
fun ShareActionRow(icon: Painter, label: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(vertical = 8.dp, horizontal = 8.dp),//
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(Theme.color.surfaces.surfaceContainer, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = icon,
                contentDescription = label,
                tint = Theme.color.surfaces.onSurfaceContainer,
                modifier = Modifier.size(24.dp)
            )
        }
        Text(
            text = label,
            color = Theme.color.surfaces.onSurfaceContainer,
            style = Theme.textStyle.body.mediumMedium14
        )
    }
}
