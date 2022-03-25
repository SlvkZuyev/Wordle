package com.slvkdev.wordle.keyboard.model

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.slvkdev.wordle.common.IndicationColors

class KeyboardStyle(
    val letterStyle: TextStyle = TextStyle(fontSize = 24.sp),
    val indicationColors: IndicationColors = IndicationColors()
){

}