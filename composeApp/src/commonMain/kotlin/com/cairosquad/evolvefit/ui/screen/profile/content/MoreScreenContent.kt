package com.cairosquad.evolvefit.ui.screen.profile.content

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_ruler
import org.jetbrains.compose.resources.painterResource

@Composable
fun MoreScreenContent() {
    Column(
        modifier = Modifier.fillMaxSize().background(color = Theme.color.surfaces.surface)
            .systemBarsPadding().padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ProfileInfoColumn("","",modifier = Modifier.padding(bottom = 24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(color = Theme.color.surfaces.surfaceContainer)
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.Start) {
                Row(
                    modifier = Modifier.padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_ruler),
                        contentDescription = "Ruler",

                        )
                    Text(
                        text = "",
                        style = Theme.textStyle.label.smallRegular12,
                        color = Theme.color.surfaces.onSurfaceVariant
                    )
                }
                Text(
                    text= "",
                    style = Theme.textStyle.body.mediumMedium14,
                    color = Theme.color.surfaces.onSurfaceContainer
                )
            }
            Box(modifier = Modifier.border(width = 1.dp, color = Theme.color.surfaces.outlineVariant))

        }
    }
}

@Composable
fun ProfileInfoColumn(
    userName: String,
    userEmail: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        NetworkImage(
            model = userName,
            contentDescription = "Profile Image",
            modifier = Modifier.size(80.dp).clip(shape = CircleShape)
        )
        Text(
            text = userEmail,
            color = Theme.color.surfaces.onSurfaceContainer,
            style = Theme.textStyle.label.mediumMedium14
        )
        Text(
            text = "",
            color = Theme.color.surfaces.onSurfaceVariant,
            style = Theme.textStyle.label.smallRegular12
        )
    }
}