package com.cairosquad.evolvefit.ui.screen.more.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.component.UiImageDisplayer
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_app_logo
import org.jetbrains.compose.resources.painterResource


@Composable
fun LogoutBottomSheetContent(
    onLogout: () -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UiImageDisplayer(
            image = painterResource(Res.drawable.ic_app_logo) as UiImage,
            contentDescription = "logo",
            modifier = Modifier.size(80.dp).padding(bottom = 16.dp)
        )
        Text(
            text = "Confirm Logout",
            style = Theme.textStyle.label.mediumMedium16,
            color = Theme.color.surfaces.onSurface,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Are you sure you want to log out?",
            style = Theme.textStyle.label.mediumMedium12,
            color = Theme.color.surfaces.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 40.dp)
        )
        PrimaryButton(
            text = "Logout",
            onClick = onLogout,
            modifier = Modifier.padding(bottom = 16.dp),
            isEnabled = true,
            enabledTextColor = Theme.color.brand.onPrimary,
            textStyle = Theme.textStyle.body.mediumMedium14,
        )
    }
}