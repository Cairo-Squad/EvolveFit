package com.cairosquad.evolvefit.design_system.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = Theme.color.surfaces.surface,
    scrimColor: Color = Theme.color.surfaces.onSurfaceAt2,
    content: @Composable ColumnScope.() -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var isBottomSheetVisible by remember { mutableStateOf(isVisible) }
    LaunchedEffect(isVisible) {
        if (isVisible) {
            isBottomSheetVisible = true
        } else {
            sheetState.hide()
            isBottomSheetVisible = false
        }
    }

    if (isBottomSheetVisible) {
        ModalBottomSheet(
            modifier = modifier.padding(8.dp),
            onDismissRequest = onDismiss,
            sheetState = sheetState,
            containerColor = containerColor,
            scrimColor = scrimColor
        ) {
            content()
        }
    }
}