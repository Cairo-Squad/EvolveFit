package com.cairosquad.evolvefit.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.adamglin.composeshadow.dropShadow
import com.cairosquad.evolvefit.design_system.component.CheckboxItem
import com.cairosquad.evolvefit.design_system.component.CheckboxStyle
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.report.ReportScreenState
import org.jetbrains.compose.resources.stringResource

@Composable
fun DropdownMenu(
    items: List<String>,
    selectedItem: String?,
    isExpanded: Boolean,
    onItemClicked: (String) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = isExpanded,
        enter = fadeIn(animationSpec = tween(500)),
        exit = fadeOut(animationSpec = tween(500))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Theme.color.surfaces.surfaceContainer)
                .padding(8.dp)
        ) {
            items.forEach { item ->
                val isSelected = item == selectedItem

                CheckboxItem(
                    text = item,
                    isChecked = isSelected,
                    onCheckedChange = {
                        onItemClicked(item)
                        onDismissRequest()
                    }, style = CheckboxStyle.Tick
                )
            }
        }
    }
}

@Composable
fun DropdownMenu(
    items: List<ReportScreenState.WeekItem>,
    selectedItem: ReportScreenState.WeekItem,
    isExpanded: Boolean,
    onItemClicked: (ReportScreenState.WeekItem) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier.fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = { onDismissRequest() })
            }
    ) {
        AnimatedVisibility(
            modifier = modifier,
            visible = isExpanded,
        ) {
            Column(
                modifier = modifier
                    .offset(y = (-8).dp)
                    .dropShadow(
                        shape = RoundedCornerShape(8.dp),
                        color = Theme.color.surfaces.dropShadow,
                        offsetY = 40.dp,
                        offsetX = 0.dp,
                        blur = 80.dp,
                        spread = 0.dp,
                    )
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Theme.color.surfaces.surfaceContainer)
                    .padding(8.dp)
            ) {
                items.forEach { item ->
                    val isSelected = item.key == selectedItem.key

                    CheckboxItem(
                        text = stringResource(item.label),
                        isChecked = isSelected,
                        onCheckedChange = {
                            onItemClicked(item)
                            onDismissRequest()
                        }, style = CheckboxStyle.Tick
                    )
                }
            }
        }
    }
}