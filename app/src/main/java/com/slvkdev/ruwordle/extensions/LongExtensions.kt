package com.slvkdev.ruwordle.extensions

import java.util.concurrent.TimeUnit

fun Long.formatTime(timeFormat: String): String = String.format(
    timeFormat,
    TimeUnit.MILLISECONDS.toHours(this),
    TimeUnit.MILLISECONDS.toMinutes(this) % 60,
    TimeUnit.MILLISECONDS.toSeconds(this) % 60
)