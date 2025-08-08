package com.cairosquad.evolvefit.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.BottomSheet
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.util.FIRST_YEAR
import com.cairosquad.evolvefit.ui.util.getDateFromYearMonthDay
import com.cairosquad.evolvefit.ui.util.getDayFromDate
import com.cairosquad.evolvefit.ui.util.getMonthAsInt
import com.cairosquad.evolvefit.ui.util.getMonthFromDate
import com.cairosquad.evolvefit.ui.util.getNumberOfDaysInMonth
import com.cairosquad.evolvefit.ui.util.getYearFromDate
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DateBottomSheet(
    dateOfBirth: String,
    isDatePickerBottomSheetOpen: Boolean,
    onDatePickerDismiss: () -> Unit,
    onDateChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val day = getDayFromDate(dateOfBirth)
    val dayPagerState = rememberPagerState(
        initialPage = day.toInt() - 1,
        pageCount = {
            getNumberOfDaysInMonth(
                year = getYearFromDate(dateOfBirth).toInt(),
                month = getMonthAsInt(getMonthFromDate(dateOfBirth))
            )
        }
    )

    val monthPagerState = rememberPagerState(
        initialPage = getMonthAsInt(getMonthFromDate(dateOfBirth)) - 1,
        pageCount = { 12 }
    )

    val currentYear = getYearFromDate(dateOfBirth).toInt()

    val yearPagerState = rememberPagerState(
        initialPage = currentYear - FIRST_YEAR,
        pageCount = { currentYear - FIRST_YEAR + 1 }
    )

    BottomSheet(
        isVisible = isDatePickerBottomSheetOpen,
        onDismiss = onDatePickerDismiss,
        modifier = modifier
            .fillMaxWidth()
    ) {
        DateBottomSheetContent(
            dayPagerState = dayPagerState,
            monthPagerState = monthPagerState,
            yearPagerState = yearPagerState,
            onDateChange = {
                onDateChange(
                    getDateFromYearMonthDay(
                        year = yearPagerState.currentPage + FIRST_YEAR,
                        month = monthPagerState.currentPage + 1,
                        day = dayPagerState.currentPage + 1
                    )
                )
            },
            onDatePickerDismiss = onDatePickerDismiss,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}

@Composable
fun DateBottomSheetContent(
    dayPagerState: PagerState,
    monthPagerState: PagerState,
    yearPagerState: PagerState,
    onDateChange: () -> Unit,
    onDatePickerDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Birthday", // TODO
            style = Theme.textStyle.label.mediumMedium16,
            color = Theme.color.surfaces.onSurface,
            modifier = Modifier
                .padding(bottom = 4.dp)
        )

        Text(
            text = "Please select your birthday", // TODO
            style = Theme.textStyle.label.mediumMedium12,
            color = Theme.color.surfaces.onSurfaceVariant,
            modifier = Modifier
                .padding(bottom = 16.dp)
        )

        DatePicker(
            dayPagerState = dayPagerState,
            monthPagerState = monthPagerState,
            yearPagerState = yearPagerState,
            modifier = Modifier
                .padding(bottom = 40.dp)
                .fillMaxWidth()
        )

        PrimaryButton(
            text = "Save", // TODO
            onClick = {
                onDateChange()
                onDatePickerDismiss()
            }
        )
    }
}

@Preview
@Composable
fun PreviewDateBottomSheet() {
    AppTheme {
        DateBottomSheet(
            dateOfBirth = "2023-08-06",
            onDateChange = {},
            isDatePickerBottomSheetOpen = true,
            onDatePickerDismiss = {},
            modifier = Modifier
                .padding(16.dp)
        )
    }
}