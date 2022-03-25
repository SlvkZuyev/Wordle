package com.slvkdev.wordle.field

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.slvkdev.wordle.field.models.WordleFieldDimensions


@Composable
fun calculateWordleFieldDimensions(maxHeight: Dp, maxWidth: Dp): WordleFieldDimensions {
    val nCols = 5
    val nRows = 6
    val verticalAspectRatio = 0.95f
    val horizontalAspectRatio = 1.15f
    val paddingValue = 1.dp

    fun calculateVerticalAxisFirst(): WordleFieldDimensions {
        val squareHeight = maxHeight / nRows - paddingValue
        val squareWidth = squareHeight * verticalAspectRatio
        val fieldWidth = (squareWidth + paddingValue) * nCols
        return WordleFieldDimensions(
            squareWith = squareWidth,
            squareHeight = squareHeight,
            squarePadding = PaddingValues(paddingValue),
            fieldWidth = fieldWidth,
            fieldHeight = maxHeight
        )
    }

    fun calculateHorizontalAxisFirst(): WordleFieldDimensions {
        val squareWidth = maxWidth / nCols - paddingValue
        val squareHeight = squareWidth * horizontalAspectRatio
        val fieldHeight = (squareWidth + paddingValue) * nRows
        return WordleFieldDimensions(
            squareWith = squareWidth,
            squareHeight = squareHeight,
            squarePadding = PaddingValues(paddingValue),
            fieldWidth = maxWidth,
            fieldHeight = fieldHeight
        )
    }

    val verticalFirstDimensions = calculateVerticalAxisFirst()
    if (verticalFirstDimensions.fieldWidth <= maxWidth) {
        return verticalFirstDimensions
    }

    return calculateHorizontalAxisFirst()


}