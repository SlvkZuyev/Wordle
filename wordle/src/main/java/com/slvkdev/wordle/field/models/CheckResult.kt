package com.slvkdev.wordle.field.models


sealed class CheckResult{
    object InputIsInvalid: CheckResult()
    class Success(val squares: List<FieldSquare>): CheckResult()
}