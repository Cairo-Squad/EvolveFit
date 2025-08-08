package com.cairosquad.evolvefit.ui.util

import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.month_april
import evolvefit.composeapp.generated.resources.month_august
import evolvefit.composeapp.generated.resources.month_december
import evolvefit.composeapp.generated.resources.month_february
import evolvefit.composeapp.generated.resources.month_january
import evolvefit.composeapp.generated.resources.month_july
import evolvefit.composeapp.generated.resources.month_june
import evolvefit.composeapp.generated.resources.month_march
import evolvefit.composeapp.generated.resources.month_may
import evolvefit.composeapp.generated.resources.month_november
import evolvefit.composeapp.generated.resources.month_october
import evolvefit.composeapp.generated.resources.month_september
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.getString

const val FIRST_YEAR = 1900

fun getDayFromDate(date: String): String {
    return (date.split("-")[2].toInt()).toString()
}

fun getMonthFromDate(date: String): String {
    return getMonthName(
        month = date.split("-")[1].toInt()
    )
}

fun getYearFromDate(date: String): String {
    return date.split("-")[0]

}

fun getMonthAsInt(month: String): Int {
    return runBlocking {
        when (month) {
            getString(Res.string.month_january) -> 1
            getString(Res.string.month_february) -> 2
            getString(Res.string.month_march) -> 3
            getString(Res.string.month_april) -> 4
            getString(Res.string.month_may) -> 5
            getString(Res.string.month_june) -> 6
            getString(Res.string.month_july) -> 7
            getString(Res.string.month_august) -> 8
            getString(Res.string.month_september) -> 9
            getString(Res.string.month_october) -> 10
            getString(Res.string.month_november) -> 11
            getString(Res.string.month_december) -> 12
            else -> throw IllegalArgumentException("Invalid month name: $month")
        }
    }
}

fun getNumberOfDaysInMonth(year: Int, month: Int): Int {
    return when (month) {
        1, 3, 5, 7, 8, 10, 12 -> 31
        4, 6, 9, 11 -> 30
        2 -> if (isLeapYear(year)) 29 else 28
        else -> throw IllegalArgumentException("Invalid month number: $month") // redundant because of require()
    }
}

private fun isLeapYear(year: Int): Boolean {
    return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
}

fun getMonthName(month: Int): String {
    return runBlocking {
        when (month) {
            1 -> getString(Res.string.month_january)
            2 -> getString(Res.string.month_february)
            3 -> getString(Res.string.month_march)
            4 -> getString(Res.string.month_april)
            5 -> getString(Res.string.month_may)
            6 -> getString(Res.string.month_june)
            7 -> getString(Res.string.month_july)
            8 -> getString(Res.string.month_august)
            9 -> getString(Res.string.month_september)
            10 -> getString(Res.string.month_october)
            11 -> getString(Res.string.month_november)
            12 -> getString(Res.string.month_december)
            else -> throw IllegalArgumentException("Invalid month number: $month")
        }
    }
}

fun getDateFromYearMonthDay(year: Int, month: Int, day: Int): String {
    // TODO
    return "2023-08-06"
}