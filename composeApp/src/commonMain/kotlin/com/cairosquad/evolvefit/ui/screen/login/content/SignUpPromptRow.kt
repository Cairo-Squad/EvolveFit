package com.cairosquad.evolvefit.ui.screen.login.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.do_not_have_an_account
import evolvefit.composeapp.generated.resources.join_now
import org.jetbrains.compose.resources.stringResource

@Composable
fun SignUpPromptRow(
    onJoinNowClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 74.dp)
            .padding(bottom = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(Res.string.do_not_have_an_account),
            style = Theme.textStyle.label.mediumMedium14,
            color = Theme.color.surfaces.onSurfaceVariant,
        )
        Text(
            modifier = Modifier.clickable(onClick = onJoinNowClicked),
            text = stringResource(Res.string.join_now),
            style = Theme.textStyle.label.mediumMedium16,
            color = Theme.color.brand.primary,
        )
    }
}
