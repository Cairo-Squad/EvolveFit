package com.cairosquad.evolvefit.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.util.FIRST_YEAR
import com.cairosquad.evolvefit.ui.util.getMonthAsInt
import com.cairosquad.evolvefit.ui.util.getMonthFromDate
import com.cairosquad.evolvefit.ui.util.getMonthName
import com.cairosquad.evolvefit.ui.util.getNumberOfDaysInMonth
import com.cairosquad.evolvefit.ui.util.getYearFromDate
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DatePicker(
    dayPagerState: PagerState,
    monthPagerState: PagerState,
    yearPagerState: PagerState,
    modifier: Modifier = Modifier
) {

    val days = remember {
        (1..getNumberOfDaysInMonth(
            year = yearPagerState.currentPage + FIRST_YEAR,
            month = monthPagerState.currentPage + 1
        )).map { number ->
            if (number < 10) "0$number" else number.toString()
        }
    }

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
            VerticalPicker(
                state = dayPagerState,
                options = days,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            )

            // Month
            VerticalPicker(
                state = monthPagerState,
                options = (1..12).map { getMonthName(it) },
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            )

            // Year
            VerticalPicker(
                state = yearPagerState,
                options = (MIN_YEAR..MAX_YEAR).map { it.toString() },
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
private fun VerticalPicker(
    state: PagerState,
    options: List<String>,
    modifier: Modifier = Modifier
) {
    VerticalPager(
        state = state,
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        pageSpacing = 4.dp,
        contentPadding = PaddingValues(vertical = 106.dp),
        pageSize = PageSize.Fixed(40.dp),
    ) { page ->
        val animatedContentColor by animateColorAsState(
            targetValue = if (page == state.currentPage) Theme.color.surfaces.onSurfaceContainer else Theme.color.surfaces.outlineVariant
        )

        Box(
            modifier = Modifier
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = options.getOrNull(page) ?: "",
                style = Theme.textStyle.body.mediumMedium14, // TODO: There is a missing text style when not selected (Poppins/Title 14 Regular)
                color = animatedContentColor,
                modifier = Modifier
            )
        }
    }
}

private const val MIN_YEAR = 1900
private const val MAX_YEAR = 2100
private const val INITIAL_DATE = "2025-08-06"

@Preview
@Composable
fun PreviewDatePicker() {

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