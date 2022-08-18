package com.slvkdev.ruwordle.composables

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.slvkdev.ruwordle.extensions.formatTime

class CountDownTimerState(durationMillis: Long, onFinish: () -> Unit) {
    private val timeFormat = "%02d:%02d:%02d"
    var time by mutableStateOf(durationMillis.formatTime(timeFormat))

    private val countDownTimer = object : CountDownTimer(durationMillis, 1000) {
        override fun onTick(millisRemaining: Long) {
            time = millisRemaining.formatTime(timeFormat)
        }

        override fun onFinish() {
            Log.d("CountDownTimer", "Timer Finished for duration ${durationMillis}")
            onFinish()
        }
    }

    init {
        if (durationMillis > 0) {
            Log.d("CountDownTimer", "Timer started with duration: ${durationMillis}")
            countDownTimer.start()
        }

    }
}

@Composable
fun rememberCountDownTimerState(durationMillis: Long, onFinish: () -> Unit) =
    remember(durationMillis, onFinish) {
        CountDownTimerState(durationMillis = durationMillis, onFinish = onFinish)
    }

@Composable
fun CountDownTimer(
    modifier: Modifier = Modifier,
    durationSeconds: Long,
    style: TextStyle = MaterialTheme.typography.h3.copy(fontWeight = FontWeight.Bold),
    onFinish: () -> Unit,
) {
    val timerState =
        rememberCountDownTimerState(durationMillis = durationSeconds * 1000, onFinish = onFinish)
    Text(
        modifier = modifier,
        text = timerState.time,
        style = style,
    )
}




