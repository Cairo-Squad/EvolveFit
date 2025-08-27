package com.cairosquad.evolvefit.design_system.text_styles

import androidx.compose.ui.text.TextStyle

data class DisplayTextStyle(
    val largeBold24: TextStyle,
    val largeBold20: TextStyle,
    val mediumMedium20: TextStyle,
    val largeBold18: TextStyle,
)
data class HeadlineTextStyle(
    val largeBold18: TextStyle,
    val mediumMedium18: TextStyle,
    val largeBold16: TextStyle,
    val mediumMedium16: TextStyle,
)
data class TitleTextStyle(
    val largeBold16: TextStyle,
    val mediumMedium16: TextStyle,
    val largeBold14: TextStyle,
    val mediumMedium14: TextStyle,
)
data class LabelTextStyle(
    val mediumMedium16: TextStyle,
    val mediumMedium14: TextStyle,
    val smallRegular14: TextStyle,
    val mediumMedium12: TextStyle,
    val smallRegular12: TextStyle,
)
data class BodyTextStyle(
    val smallRegular16: TextStyle,
    val mediumMedium14: TextStyle,
    val mediumMedium12: TextStyle,
    val smallRegular10: TextStyle,
)
data class AppTextStyle(
    val display: DisplayTextStyle,
    val headline: HeadlineTextStyle,
    val title: TitleTextStyle,
    val label: LabelTextStyle,
    val body: BodyTextStyle,
)