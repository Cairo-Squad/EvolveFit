package com.cairosquad.evolvefit.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerDefaults
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            state = dayPagerState
        ) {

        }
    }
}

@Composable
fun VerticalNumberPager(

) {
    val pagerState = rememberPagerState(
        pageCount = { 13 }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surface)
    ) {
        VerticalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            pageSpacing = 0.dp,
            userScrollEnabled = true,
            reverseLayout = false,
            contentPadding = PaddingValues(0.dp),
            pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
                state = pagerState,
                orientation = Orientation.Vertical
            )
        ) { page ->
            val displayNumber = "${page + 7}" // TODO

            NumberPageContent(
                number = displayNumber,
                isCurrentPage = page == pagerState.currentPage,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun NumberPageContent(
    number: String,
    isCurrentPage: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(Color.Black)
            .padding(horizontal = 32.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = number,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = if (isCurrentPage) Color.White else Color.Gray,
            fontFamily = FontFamily.Monospace
        )
    }
}

@Composable
fun StyledVerticalNumberPager() {
    val pagerState = rememberPagerState(
        initialPage = 3,
        pageCount = { 13 }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        VerticalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            pageSpacing = 8.dp
        ) { page ->
            val displayNumber = "${page + 7}" // TODO
            val isCurrentPage = page == pagerState.currentPage

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        if (isCurrentPage) Color(0xFF1A1A1A) else Color.Black
                    )
                    .padding(horizontal = 32.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = displayNumber,
                    fontSize = if (isCurrentPage) 56.sp else 42.sp,
                    fontWeight = if (isCurrentPage) FontWeight.Bold else FontWeight.Normal,
                    color = when {
                        isCurrentPage -> Color.White
                        kotlin.math.abs(page - pagerState.currentPage) == 1 -> Color.Gray
                        else -> Color.DarkGray
                    },
                    fontFamily = FontFamily.Monospace
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