package com.cairosquad.evolvefit.ui.screen.home.content.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.util.toString
import com.cairosquad.evolvefit.viewmodel.home.HomeScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.activity
import evolvefit.composeapp.generated.resources.completed
import evolvefit.composeapp.generated.resources.current_weight
import evolvefit.composeapp.generated.resources.ic_crown
import evolvefit.composeapp.generated.resources.ic_progress
import evolvefit.composeapp.generated.resources.ic_scale
import evolvefit.composeapp.generated.resources.ic_thin_check_mark
import evolvefit.composeapp.generated.resources.the_goal
import evolvefit.composeapp.generated.resources.this_week
import evolvefit.composeapp.generated.resources.your_progress
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun HomeProgressBox(
    progress: HomeScreenState.WeeklyProgressUiState?,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(visible = progress != null) {
        Column(
            modifier = modifier
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .background(Theme.color.surfaces.surfaceContainer)
                .padding(
                    horizontal = 12.dp,
                    vertical = 16.dp
                ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ProgressHeaderRow()

            WeeklyProgressBar(
                progressDays = progress?.progressDays ?: emptyMap()
            )

            progress?.goal?.let {
                progress.weightUnit?.let { resource ->
                    StatsRow(
                        goal = stringResource(it),
                        currentWeight = progress.currentWeight,
                        weightUnit = stringResource(resource),
                        activityPercentage = progress.activityPercentage
                    )
                }
            }
        }
    }
}

@Composable
private fun ProgressHeaderRow(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(Res.string.your_progress),
            style = Theme.textStyle.headline.largeBold16,
            color = Theme.color.surfaces.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(end = 8.dp)
        )

        Text(
            text = stringResource(Res.string.this_week),
            style = Theme.textStyle.label.smallRegular14,
            color = Theme.color.surfaces.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

private const val WEEK_DAYS_NUM = 7

@Composable
private fun WeeklyProgressBar(
    progressDays: Map<Int, Boolean>,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        maxItemsInEachRow = WEEK_DAYS_NUM
    ) {
        progressDays.entries.forEach { entry ->
            DayBox(
                day = entry.key,
                isCompleted = entry.value,
                modifier = Modifier
                    .then(
                        if (entry.key != progressDays.entries.last().key) {
                            Modifier
                                .padding(end = 8.dp)
                        } else {
                            Modifier
                        }
                    )
            )
        }
    }
}

@Composable
private fun DayBox(
    day: Int,
    isCompleted: Boolean,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isCompleted) {
        Theme.color.brand.primary
    } else {
        Theme.color.surfaces.outlineVariant
    }

    Box(
        modifier = modifier
            .clip(CircleShape)
            .size(36.dp)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        AnimatedContent(
            targetState = isCompleted,
            transitionSpec = {
                fadeIn().togetherWith(fadeOut())
            }
        ) {
            if (it) {
                Icon(
                    painter = painterResource(Res.drawable.ic_thin_check_mark),
                    contentDescription = stringResource(Res.string.completed),
                    tint = Theme.color.brand.onPrimary,
                    modifier = Modifier
                        .size(24.dp)
                )
            } else {
                Text(
                    text = day.toString(),
                    style = Theme.textStyle.body.mediumMedium14,
                    color = Theme.color.surfaces.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun StatsRow(
    goal: String,
    currentWeight: Float,
    weightUnit: String,
    activityPercentage: UInt,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 8.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        StatsSection(
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(0.28f),
            iconRes = Res.drawable.ic_crown,
            title = stringResource(Res.string.the_goal),
            value = goal,
        )

        VerticalDivider(
            color = Theme.color.surfaces.outlineVariant,
            thickness = 1.dp,
            modifier = Modifier
                .height(40.dp)
        )

        StatsSection(
            modifier = Modifier
                .weight(0.43f)
                .padding(start = 16.dp),
            iconRes = Res.drawable.ic_scale,
            title = stringResource(Res.string.current_weight),
            value = "${currentWeight.toString(1)} $weightUnit",
        )

        VerticalDivider(
            color = Theme.color.surfaces.outlineVariant,
            thickness = 1.dp,
            modifier = Modifier
                .height(40.dp)
        )

        StatsSection(
            modifier = Modifier
                .padding(end = 8.dp)
                .weight(0.28f)
                .padding(start = 16.dp),
            iconRes = Res.drawable.ic_progress,
            title = stringResource(Res.string.activity),
            value = "$activityPercentage %",
        )
    }
}

@Composable
private fun StatsSection(
    iconRes: DrawableResource,
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = title,
                tint = Theme.color.brand.primary,
                modifier = Modifier
                    .size(16.dp)
            )

            Text(
                text = title,
                style = Theme.textStyle.label.smallRegular12,
                color = Theme.color.surfaces.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

        Text(
            text = value,
            style = Theme.textStyle.body.mediumMedium14,
            color = Theme.color.surfaces.onSurfaceContainer,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

