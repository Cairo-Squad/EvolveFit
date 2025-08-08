package com.cairosquad.evolvefit.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.util.getDayFromDate
import com.cairosquad.evolvefit.ui.util.getMonthAsInt
import com.cairosquad.evolvefit.ui.util.getMonthFromDate
import com.cairosquad.evolvefit.ui.util.getMonthName
import com.cairosquad.evolvefit.ui.util.getNumberOfDaysInMonth
import com.cairosquad.evolvefit.ui.util.getYearFromDate
import org.jetbrains.compose.ui.tooling.preview.Preview

const val MIN_YEAR = 1900
const val MAX_YEAR = 2100
const val INITIAL_DATE = "2025-08-06"

@Composable
fun DatePicker(
    dayPagerState: PagerState,
    monthPagerState: PagerState,
    yearPagerState: PagerState,
    modifier: Modifier = Modifier
) {


    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(252.dp),
    ) {
        ChoiceIndicator(
            modifier = Modifier
                .align(Alignment.Center)
        )

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
        ) {
            // Day
            VerticalDatePicker(
                state = dayPagerState,
                content = (1..getNumberOfDaysInMonth(
                    year = yearPagerState.currentPage,
                    month = monthPagerState.currentPage
                )).map { number ->
                    if (number < 10) "0$number" else number.toString()
                },
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            )

            // Month
            VerticalDatePicker(
                state = monthPagerState,
                content = (1..12).map { getMonthName(it) },
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            )

            // Year
            VerticalDatePicker(
                state = yearPagerState,
                content = (MIN_YEAR..MAX_YEAR).map { it.toString() },
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            )
        }
    }
}

@Composable
private fun ChoiceIndicator(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .height(40.dp)
            .background(Theme.color.surfaces.surfaceContainer)
    )
}

@Composable
private fun VerticalDatePicker(
    state: PagerState,
    content: List<String>,
    modifier: Modifier = Modifier
) {
    VerticalPager(
        state = state,
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        pageSpacing = 4.dp,
        contentPadding = PaddingValues(106.dp),
        pageSize = PageSize.Fixed(40.dp),
    ) { page ->
        val animatedContentColor by animateColorAsState(
            targetValue = if (page == state.currentPage) Theme.color.surfaces.onSurfaceContainer else Theme.color.surfaces.outlineVariant
        )

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .background(
                    color = if (page == state.currentPage) Theme.color.surfaces.surfaceContainer else Color.Transparent
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = content.getOrNull(page) ?: "",
                style = Theme.textStyle.body.mediumMedium14, // TODO: There is a missing text style when not selected (Poppins/Title 14 Regular)
                color = animatedContentColor,
                modifier = Modifier
            )
        }
    }
}

@Preview
@Composable
fun PreviewDatePicker() {

    val day by remember { mutableStateOf(getDayFromDate(INITIAL_DATE)) }
    val month by remember { mutableStateOf(getMonthFromDate(INITIAL_DATE)) }
    val year by remember { mutableStateOf(getYearFromDate(INITIAL_DATE)) }

    val dayPagerState =
        rememberPagerState { getNumberOfDaysInMonth(year.toInt(), getMonthAsInt(month)) }
    val monthPagerState = rememberPagerState { 12 }
    val yearPagerState = rememberPagerState { MAX_YEAR - MIN_YEAR + 1 }

    AppTheme(isDarkTheme = true) {
        DatePicker(
            dayPagerState = dayPagerState,
            monthPagerState = monthPagerState,
            yearPagerState = yearPagerState,
            modifier = Modifier
                .background(Theme.color.surfaces.surface)
                .padding(horizontal = 16.dp)
        )
    }
}