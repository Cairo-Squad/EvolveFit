package com.cairosquad.evolvefit.design_system.component.appbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CustomAppBar(
    title: String,
    modifier: Modifier = Modifier,
    contentColor: Color = Theme.color.surfaces.onSurface,
    navigationIcon: (@Composable (() -> Unit))? = null,
    actions: (@Composable RowScope.() -> Unit)? = null
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = 4.dp),
            text = title,
            color = contentColor,
            style = Theme.textStyle.title.largeBold16
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (navigationIcon != null) {
                navigationIcon()
            } else {
                Box(modifier = Modifier)
            }

            if (actions != null) {
                Row(content = actions)
            } else {
                Box(modifier = Modifier)
            }
        }
    }
}

@Preview
@Composable
private fun CustomAppBarPreview() {


        AppTheme(isDarkTheme = false) {
            Row  (
                modifier = Modifier.background(Theme.color.surfaces.surface),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                CustomAppBar(title = "Title Only")

                CustomAppBar(
                    title = "With Back",
                    navigationIcon = {
                        ActionIconButton(
                            icon = painterResource(Res.drawable.ic_back),
                            contentDescription = "Back",
                            tint = Theme.color.surfaces.onSurface,
                            onClick = {}
                        )
                    }
                )

                CustomAppBar(
                    title = "With Actions",
                    actions = {
                        ActionIconButton(
                            icon = painterResource(Res.drawable.ic_share),
                            contentDescription = "Share",
                            tint = Theme.color.surfaces.onSurface,
                            onClick = {}
                        )
                    }
                )

                CustomAppBar(
                    title = "Back & One Action",
                    navigationIcon = {
                        ActionIconButton(
                            icon = painterResource(Res.drawable.ic_back),
                            contentDescription = "Back",
                            tint = Theme.color.surfaces.onSurface,
                            onClick = {}
                        )
                    },
                    actions = {
                        ActionIconButton(
                            icon = painterResource(Res.drawable.ic_group),
                            contentDescription = "Group",
                            tint = Theme.color.surfaces.onSurface,
                            onClick = {}
                        )
                    }
                )

                CustomAppBar(
                    title = "Back & Multi Actions",
                    navigationIcon = {
                        ActionIconButton(
                            icon = painterResource(Res.drawable.ic_back),
                            contentDescription = "Back",
                            tint = Theme.color.surfaces.onSurface,
                            onClick = {}
                        )
                    },
                    actions = {
                        ActionIconButton(
                            icon = painterResource(Res.drawable.ic_bookmark),
                            contentDescription = "Bookmark",
                            tint = Theme.color.surfaces.onSurface,
                            onClick = {}
                        )
                        ActionIconButton(
                            icon = painterResource(Res.drawable.ic_share),
                            contentDescription = "Share",
                            tint = Theme.color.surfaces.onSurface,
                            onClick = {}
                        )
                    }
                )
            }
        }

        AppTheme(isDarkTheme = true) {
            Row(
                modifier = Modifier.background(Theme.color.surfaces.surface),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                CustomAppBar(title = "Title Only")

                CustomAppBar(
                    title = "With Back",
                    navigationIcon = {
                        ActionIconButton(
                            icon = painterResource(Res.drawable.ic_back),
                            contentDescription = "Back",
                            tint = Theme.color.surfaces.onSurface,
                            onClick = {}
                        )
                    }
                )

                CustomAppBar(
                    title = "With Actions",
                    actions = {
                        ActionIconButton(
                            icon = painterResource(Res.drawable.ic_share),
                            contentDescription = "Share",
                            tint = Theme.color.surfaces.onSurface,
                            onClick = {}
                        )
                    }
                )

                CustomAppBar(
                    title = "Back & One Action",
                    navigationIcon = {
                        ActionIconButton(
                            icon = painterResource(Res.drawable.ic_back),
                            contentDescription = "Back",
                            tint = Theme.color.surfaces.onSurface,
                            onClick = {}
                        )
                    },
                    actions = {
                        ActionIconButton(
                            icon = painterResource(Res.drawable.ic_group),
                            contentDescription = "Group",
                            tint = Theme.color.surfaces.onSurface,
                            onClick = {}
                        )
                    }
                )

                CustomAppBar(
                    title = "Back & Multi Actions",
                    navigationIcon = {
                        ActionIconButton(
                            icon = painterResource(Res.drawable.ic_back),
                            contentDescription = "Back",
                            tint = Theme.color.surfaces.onSurface,
                            onClick = {}
                        )
                    },
                    actions = {
                        ActionIconButton(
                            icon = painterResource(Res.drawable.ic_bookmark),
                            contentDescription = "Bookmark",
                            tint = Theme.color.surfaces.onSurface,
                            onClick = {}
                        )
                        ActionIconButton(
                            icon = painterResource(Res.drawable.ic_share),
                            contentDescription = "Share",
                            tint = Theme.color.surfaces.onSurface,
                            onClick = {}
                        )
                    }
                )
            }
        }
}

