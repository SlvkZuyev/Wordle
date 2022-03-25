package com.slvkdev.ruwordle

import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun timeChecker(){
        val now = LocalDateTime.now()
        val midnight = LocalDateTime.now().plusDays(1).with(LocalTime.MIDNIGHT)
        val delta = ChronoUnit.SECONDS.between(now, midnight);


        assert(true)
    }
}