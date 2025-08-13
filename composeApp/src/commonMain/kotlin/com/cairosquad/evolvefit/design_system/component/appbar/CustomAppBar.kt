package com.cairosquad.evolvefit.design_system.component.appbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_back
import evolvefit.composeapp.generated.resources.ic_bookmark
import evolvefit.composeapp.generated.resources.ic_group
import evolvefit.composeapp.generated.resources.ic_share
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CustomAppBar(
    title: String,
    modifier: Modifier = Modifier,
    contentColor: Color = Theme.color.surfaces.onSurface,
    header: (@Composable (() -> Unit)) = { Box(modifier = Modifier) },
    tail: (@Composable RowScope.() -> Unit) = { Box(modifier = Modifier) }
) {

    val density = LocalDensity.current
    var textStartPadding by remember { mutableStateOf(0.dp) }
    var textEndPadding by remember { mutableStateOf(0.dp) }

    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterStart)
                .onGloballyPositioned {
                    textStartPadding = with(density) { it.size.width.toDp() }
                }
        ) {
            header()
        }
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = maxOf(textStartPadding, textEndPadding))
                .padding(vertical = 4.dp),
            text = title,
            color = contentColor,
            style = Theme.textStyle.title.largeBold16,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Row(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterEnd)
                .onGloballyPositioned {
                    textEndPadding = with(density) { it.size.width.toDp() }
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            content = tail
        )

    }
}

@Preview
@Composable
private fun CustomAppBarPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        listOf(false, true).forEach { isDark ->
            AppTheme(isDarkTheme = isDark) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Theme.color.surfaces.surface)
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = if (isDark) "Dark Mode" else "Light Mode",
                        style = Theme.textStyle.title.mediumMedium16,
                        color = Theme.color.surfaces.onSurface
                    )

                    CustomAppBar(title = "Title Only")

                    CustomAppBar(
                        title = "With Back",
                        header = {
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
                        tail = {
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
                        header = {
                            ActionIconButton(
                                icon = painterResource(Res.drawable.ic_back),
                                contentDescription = "Back",
                                tint = Theme.color.surfaces.onSurface,
                                onClick = {}
                            )
                        },
                        tail = {
                            ActionIconButton(
                                icon = painterResource(Res.drawable.ic_group),
                                contentDescription = "Group",
                                tint = Theme.color.surfaces.onSurface,
                                onClick = {}
                            )
                        }
                    )

                    CustomAppBar(
                        title = "Back & Actions",
                        header = {
                            ActionIconButton(
                                icon = painterResource(Res.drawable.ic_back),
                                contentDescription = "Back",
                                tint = Theme.color.surfaces.onSurface,
                                onClick = {}
                            )
                        },
                        tail = {
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
                    CustomAppBar(
                        title = "Loooooooooooooooooooong text",
                        header = {
                            ActionIconButton(
                                icon = painterResource(Res.drawable.ic_back),
                                contentDescription = "Back",
                                tint = Theme.color.surfaces.onSurface,
                                onClick = {}
                            )
                        },
                        tail = {
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
    }
}
