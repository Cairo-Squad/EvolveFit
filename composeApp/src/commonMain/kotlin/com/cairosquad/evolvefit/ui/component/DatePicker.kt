package com.cairosquad.evolvefit.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.util.getDayFromDate
import com.cairosquad.evolvefit.ui.util.getMonthAsInt
import com.cairosquad.evolvefit.ui.util.getMonthFromDate
import com.cairosquad.evolvefit.ui.util.getNumberOfDaysInMonth
import com.cairosquad.evolvefit.ui.util.getYearFromDate
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DatePicker(
    date: String,
    onDateSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val day = getDayFromDate(date)
    val month = getMonthFromDate(date)
    val year = getYearFromDate(date)

    val dayPagerState =
        rememberPagerState { getNumberOfDaysInMonth(year.toInt(), getMonthAsInt(month)) }
    val monthPagerState = rememberPagerState { getMonthAsInt(month) }
    val yearPagerState = rememberPagerState { year.toInt() }

    Row(
        modifier = modifier
            .height(252.dp), // TODO: Should it stay fixed?
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        VerticalPager(
            state = dayPagerState,
            modifier = Modifier.weight(1f),
            pageSpacing = 4.dp,
            contentPadding = PaddingValues(106.dp),
            pageSize = PageSize.Fixed(40.dp),
        ) { page ->
            val animatedContentColor by animateColorAsState(
                targetValue = if (page == dayPagerState.currentPage) Theme.color.surfaces.onSurfaceContainer else Theme.color.surfaces.outlineVariant
            )

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(
                        color = if (page == dayPagerState.currentPage) Theme.color.surfaces.surfaceContainer else Color.Transparent
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${page + 1}",
                    style = Theme.textStyle.body.mediumMedium14, // TODO: There is a missing text style when not selected (Poppins/Title 14 Regular)
                    color = animatedContentColor,
                    modifier = Modifier
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewDatePicker() {
    AppTheme {
        var date by remember { mutableStateOf("2025-08-06") }
        DatePicker(
            date = date,
            onDateSelected = { date = it },
            modifier = Modifier
                .fillMaxWidth()
                .background(Theme.color.surfaces.surface)
                .padding(12.dp)
        )
    }
}