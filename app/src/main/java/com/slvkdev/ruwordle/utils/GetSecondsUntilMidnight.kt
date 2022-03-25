package com.slvkdev.ruwordle.utils

import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit

object GetSecondsUntilMidnight {
    operator fun invoke(): Long{
        val currentTime = LocalDateTime.now()
        val midnightTime = LocalDateTime.now().plusDays(1).with(LocalTime.MIDNIGHT)
        return ChronoUnit.SECONDS.between(currentTime, midnightTime)
    }
}