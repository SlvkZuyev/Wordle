package com.slvkdev.ruwordle.composables

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun RemainingTimeDialog(
    modifier: Modifier = Modifier,
    secondsUntilNewWord: Long = 0,
    onFinish: () -> Unit = {}
) {
    val yOffset: Dp by animateDpAsState(if (secondsUntilNewWord > 0) 0.dp else 1000.dp)

    Column(
        modifier = modifier
            .offset(y = yOffset)
            .clip(RoundedCornerShape(16.dp))
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Следующее слово", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(8.dp))
        CountDownTimer(durationSeconds = secondsUntilNewWord, onFinish = onFinish)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

