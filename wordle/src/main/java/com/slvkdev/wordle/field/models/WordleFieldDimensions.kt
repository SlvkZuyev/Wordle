package com.slvkdev.wordle.field.models

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class WordleFieldDimensions(
    val squareWith: Dp,
    val squareHeight: Dp,
    val squarePadding: PaddingValues,
    val fieldHeight: Dp,
    val fieldWidth: Dp,
)