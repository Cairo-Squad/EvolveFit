package com.cairosquad.evolvefit.design_system.text_styles

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun TextStyle(): AppTextStyle {
    val fontFamily = AppFontFamily()
    return AppTextStyle(
        display = DisplayTextStyle(
            largeBold24 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            ),
            largeBold20 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            ),
            mediumMedium20 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp
            ),
            largeBold18 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        ),
        headline = HeadlineTextStyle(
            largeBold18 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ),
            mediumMedium18 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            ),
            largeBold16 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            ),
            mediumMedium16 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        ),
        title = TitleTextStyle(
            largeBold16 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            ),
            mediumMedium16 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            ),
            largeBold14 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            ),
            mediumMedium14 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        ),
        label = LabelTextStyle(
            mediumMedium16 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            ),
            mediumMedium14 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            ),
            smallRegular14 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            ),
            mediumMedium12 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp
            ),
            smallRegular12 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        ),
        body = BodyTextStyle(
            smallRegular16 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            ),
            mediumMedium14 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            ),
            mediumMedium12 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp
            ),
            smallRegular10 = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp
            )
        )
    )
}
val DefaultTextStyle = AppTextStyle(
    display = DisplayTextStyle(
        largeBold24 = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
        largeBold20 = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
        mediumMedium20 = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium),
        largeBold18 = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
    ),
    headline = HeadlineTextStyle(
        largeBold18 = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
        mediumMedium18 = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium),
        largeBold16 = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
        mediumMedium16 = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium),
    ),
    title = TitleTextStyle(
        largeBold16 = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
        mediumMedium16 = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium),
        largeBold14 = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
        mediumMedium14 = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium),
    ),
    label = LabelTextStyle(
        mediumMedium16 = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium),
        mediumMedium14 = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium),
        smallRegular14 = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal),
        mediumMedium12 = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium),
        smallRegular12 = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal),
    ),
    body = BodyTextStyle(
        smallRegular16 = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal),
        mediumMedium14 = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium),
        mediumMedium12 = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium),
        smallRegular10 = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Normal),
    )
)