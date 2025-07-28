package com.cairosquad.evolvefit.design_system.text_styles

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.lato_bold
import evolvefit.composeapp.generated.resources.lato_medium
import evolvefit.composeapp.generated.resources.lato_regular
import org.jetbrains.compose.resources.Font


@Composable
fun LatoFontFamily(): FontFamily {
    return FontFamily(
        Font(Res.font.lato_bold, FontWeight.Bold),
        Font(Res.font.lato_medium, FontWeight.Medium),
        Font(Res.font.lato_regular, FontWeight.Normal)
    )
}
