package com.slvkdev.ruwordle.utils

object CalculateDailyWordId {
    private const val firstDayTimestamp = 1647468000

    operator fun invoke(currentTimestamp: Long, maxId: Int): Int {
        val delta = currentTimestamp - firstDayTimestamp
        val daysFromFirstWordReveal = delta / MILLIS_IN_DAY
        return daysFromFirstWordReveal.toInt() % maxId
    }

}