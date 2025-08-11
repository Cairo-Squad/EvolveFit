package com.cairosquad.evolvefit.design_system.composables

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_arrow_down
import evolvefit.composeapp.generated.resources.ic_check_mark
import evolvefit.composeapp.generated.resources.ic_date
import evolvefit.composeapp.generated.resources.ic_profile
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    error: String = "",
    readOnly: Boolean = false,
    isErrorMessageShown: Boolean = true,
    isSingleLine: Boolean = true,
    isPasswordField: Boolean = false,
    isCharacterCountVisible: Boolean = false,
    maxCharacters: Int? = 100,
    minHeight: Dp = 0.dp,
    leadingIcon: DrawableResource? = null,
    trailingIcon: DrawableResource? = null,
    trailingIconModifier: Modifier = Modifier,
    onTrailingIconClick: (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onClick: (() -> Unit)? = null
) {
    var textFieldValue by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(text = value, selection = TextRange(value.length)))
    }

    LaunchedEffect(value) {
        if (value != textFieldValue.text) {
            textFieldValue = TextFieldValue(
                text = value,
                selection = TextRange(value.length)
            )
        }
    }

    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = modifier
            .then(
                if (onClick != null) {
                    Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = onClick
                    )
                } else {
                    Modifier
                }
            )
    ) {
        BasicTextField(
            modifier = Modifier
                .heightIn(min = minHeight)
                .clip(RoundedCornerShape(8.dp))
                .background(Theme.color.surfaces.surfaceContainer)
                .padding(horizontal = 12.dp, vertical = 20.dp),
            value = textFieldValue,
            onValueChange = { newValue ->
                val filteredValue =
                    if (maxCharacters != null && newValue.text.length > maxCharacters) {
                        val truncatedText = newValue.text.take(maxCharacters)
                        newValue.copy(
                            text = truncatedText,
                            selection = TextRange(truncatedText.length.coerceAtMost(newValue.selection.start))
                        )
                    } else {
                        newValue
                    }

                textFieldValue = filteredValue
                onValueChange(filteredValue.text)
            },
            readOnly = readOnly,
            textStyle = Theme.textStyle.label.smallRegular14.copy(
                color = Theme.color.surfaces.onSurfaceContainer
            ),
            keyboardOptions = keyboardOptions,
            singleLine = isSingleLine,
            decorationBox = { innerTextField ->
                Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextFieldIcon(
                        leadingIcon,
                        error = error.isNotBlank(),
                        modifier = Modifier
                            .then(
                                if (onClick != null) {
                                    Modifier.clickable(
                                        interactionSource = interactionSource,
                                        indication = null,
                                        onClick = onClick
                                    )
                                } else {
                                    Modifier
                                }
                            )
                            .padding(end = 8.dp)
                    )
                    if (onClick != null) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = null,
                                    onClick = onClick
                                )
                        ) {
                            innerTextField()

                            if (textFieldValue.text.isEmpty()) {
                                Text(
                                    text = placeholder,
                                    style = Theme.textStyle.label.smallRegular14.copy(
                                        color = Theme.color.surfaces.onSurfaceVariant
                                    )
                                )
                            }
                        }
                    } else {
                        Box(
                            modifier = Modifier.weight(1f)
                        ) {
                            innerTextField()

                            if (textFieldValue.text.isEmpty()) {
                                Text(
                                    text = placeholder,
                                    style = Theme.textStyle.label.smallRegular14.copy(
                                        color = Theme.color.surfaces.onSurfaceVariant
                                    )
                                )
                            }
                        }
                    }
                    TextFieldIcon(
                        trailingIcon,
                        modifier = trailingIconModifier
                            .then(
                                if (onClick != null) {
                                    Modifier.clickable(
                                        interactionSource = interactionSource,
                                        indication = null,
                                        onClick = onClick
                                    )
                                } else {
                                    Modifier
                                }
                            )
                            .padding(start = 8.dp),
                        error = error.isNotBlank(),
                        onTrailingIconClick,
                    )
                }
                    if (isCharacterCountVisible && maxCharacters != null) {
                        Spacer(modifier = Modifier.weight(1f))
                        val remaining = maxCharacters - textFieldValue.text.length
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp, end = 8.dp),
                            text = "$remaining character left",
                            style = Theme.textStyle.label.smallRegular12,
                            color = Theme.color.surfaces.onSurfaceVariant,
                            textAlign = TextAlign.End
                        )
                    }
            }
            },
            cursorBrush = SolidColor(
                Theme.color.surfaces.onSurface
            ),
            visualTransformation = if (isPasswordField) PasswordVisualTransformation() else VisualTransformation.None,
        )

    }
}

@Composable
private fun TextFieldIcon(
    icon: DrawableResource? = null,
    modifier: Modifier = Modifier,
    error: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    val tintColor = if (error) {
        Theme.color.system.warning
    } else {
        Theme.color.surfaces.onSurfaceVariant
    }

    if (icon != null) {
        AnimatedContent(
            targetState = icon,
            transitionSpec = {
                scaleIn(animationSpec = tween(300)).togetherWith(scaleOut(animationSpec = tween(300)))
            }
        ) {
            Icon(
                painter = painterResource(it),
                contentDescription = null,
                tint = tintColor,
                modifier = modifier
                    .size(20.dp)
                    .then(
                        if (onClick != null) {
                            Modifier.clickable(onClick = onClick)
                        } else {
                            Modifier
                        }
                    )

            )
        }
    }
}

@Composable
@Preview
private fun PreviewInputFieldNormal() {
    InputField(
        value = "",
        onValueChange = {},
        placeholder = "Enter your name",
        leadingIcon = Res.drawable.ic_profile,
        modifier = Modifier.padding(16.dp)
    )
}


@Composable
@Preview
private fun PreviewDateInputField() {
    InputField(
        value = "25/2/2003",
        onValueChange = {},
        leadingIcon = Res.drawable.ic_date,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
@Preview
private fun PreviewInputFieldSuccess() {
    InputField(
        value = "Gehad",
        onValueChange = {},
        placeholder = "Enter your name",
        trailingIcon = Res.drawable.ic_check_mark,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
@Preview
private fun PreviewPasswordInputField() {
    InputField(
        value = "password123",
        onValueChange = {},
        placeholder = "Password",
        isPasswordField = true,
        leadingIcon = Res.drawable.ic_arrow_down,
        trailingIcon = Res.drawable.ic_arrow_down,
        onTrailingIconClick = { },
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
@Preview
private fun PreviewDropdownInputField() {
    InputField(
        value = "Option 1",
        onValueChange = {},
        placeholder = "Select option",
        trailingIcon = Res.drawable.ic_arrow_down,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
@Preview
private fun PreviewMultilineInputField() {
    InputField(
        value = "This is a longer text that spans multiple lines to show how the input" +
                " field behaves with multi-line content skdfhjdskfhejkdhgfejhgfjerhgejhfbkjfkjehfjbdjbfjdffhjdhfdj" +
                "hfekhguedhfkdjfnvkjdhfvudfhvdjkfvnkjdfvhdvhjdjvdkjvnd.",
        onValueChange = {},
        isSingleLine = false,
        maxCharacters = 3000,
        modifier = Modifier
            .padding(16.dp)
            .height(100.dp)
    )
}

@Composable
@Preview
private fun PreviewInputFieldArabicTyping() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        InputField(
            value = "محمد",
            onValueChange = {},
            placeholder = "أدخل اسمك",
            leadingIcon = Res.drawable.ic_profile,
            modifier = Modifier.padding(16.dp)
        )
    }
}
