package com.slvkdev.wordle.field.utils

import com.slvkdev.wordle.field.models.FieldSquare

object CreateEmptyGrid {
    operator fun invoke(): List<List<FieldSquare>> {
        return List(6) {
            List(5) {
                FieldSquare()
            }
        }
    }
}