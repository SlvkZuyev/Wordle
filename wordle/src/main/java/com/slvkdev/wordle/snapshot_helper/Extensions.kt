package com.slvkdev.wordle.snapshot_helper

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun List<String>.toJson(): String{
    return Json.encodeToString(this)
}