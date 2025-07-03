package com.example.yafinance.ui.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class NumberWithSpacesTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val originalText = text.text
        val formattedText = StringBuilder()

        val digitsOnly = originalText.replace(" ", "")

        for (i in digitsOnly.indices) {
            if (i > 0 && (digitsOnly.length - i) % 3 == 0) {
                formattedText.append(' ')
            }
            formattedText.append(digitsOnly[i])
        }

        return TransformedText(
            AnnotatedString(formattedText.toString()),
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    if (offset == 0) return 0
                    val spaces = (offset - 1) / 3
                    return offset + spaces
                }

                override fun transformedToOriginal(offset: Int): Int {
                    val textBeforeOffset = formattedText.substring(0, offset.coerceAtMost(formattedText.length))
                    return textBeforeOffset.replace(" ", "").length
                }
            }
        )
    }
}