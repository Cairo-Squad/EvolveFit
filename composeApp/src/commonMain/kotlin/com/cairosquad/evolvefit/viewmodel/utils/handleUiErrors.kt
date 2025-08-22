package com.cairosquad.evolvefit.viewmodel.utils

import com.cairosquad.evolvefit.domain.exception.ExceededCaloriesException
import com.cairosquad.evolvefit.domain.exception.ExceededWaterLimitException
import com.cairosquad.evolvefit.domain.exception.InternetConnectionException
import com.cairosquad.evolvefit.domain.exception.InvalidNumberFormatException
import com.cairosquad.evolvefit.domain.exception.LengthTooLargeException
import com.cairosquad.evolvefit.domain.exception.MealNotFoundException
import com.cairosquad.evolvefit.domain.exception.NetworkException
import com.cairosquad.evolvefit.domain.exception.NumberTooLargeException
import com.cairosquad.evolvefit.domain.exception.TimeoutException
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.error_exceeded_calories
import evolvefit.composeapp.generated.resources.error_exceeded_water
import evolvefit.composeapp.generated.resources.error_invalid_number
import evolvefit.composeapp.generated.resources.error_meal_not_found
import evolvefit.composeapp.generated.resources.error_no_internet
import evolvefit.composeapp.generated.resources.error_number_too_large
import evolvefit.composeapp.generated.resources.error_text_too_long
import evolvefit.composeapp.generated.resources.error_unknown
import org.jetbrains.compose.resources.StringResource

fun Throwable.toErrorMessageRes(): StringResource {
    return when (this) {
        is InvalidNumberFormatException -> Res.string.error_invalid_number
        is LengthTooLargeException -> Res.string.error_text_too_long
        is NumberTooLargeException -> Res.string.error_number_too_large
        is ExceededCaloriesException -> Res.string.error_exceeded_calories
        is ExceededWaterLimitException -> Res.string.error_exceeded_water
        is MealNotFoundException -> Res.string.error_meal_not_found
        is InternetConnectionException -> Res.string.error_no_internet
        is TimeoutException -> Res.string.error_no_internet
        is NetworkException -> Res.string.error_no_internet
        else -> Res.string.error_unknown
    }
}