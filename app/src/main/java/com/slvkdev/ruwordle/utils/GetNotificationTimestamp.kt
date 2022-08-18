package com.slvkdev.ruwordle.utils

object GetNotificationTimestamp {
    operator fun invoke(): Long{
        return System.currentTimeMillis() + GetSecondsUntilMidnight() * 1000 + 12 * MILLIS_IN_HOUR
    }
}