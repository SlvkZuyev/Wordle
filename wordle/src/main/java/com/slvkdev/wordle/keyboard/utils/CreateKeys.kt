package com.slvkdev.wordle.keyboard.utils

import com.slvkdev.wordle.common.LetterState
import com.slvkdev.wordle.keyboard.model.Key


object CreateKeys {
    private val letters = listOf(
        "й", "ц","у","к","е","н","г","ш","щ","з",
        "х","ъ","ф","ы","в","а","п","р","о","л",
        "д","ж","э","я","ч","с","м","и","т","ь",
        "б","ю"
    )
    operator fun invoke(): List<Key>{
        return List(letters.size){
            Key(
                text = letters[it],
                initialState = LetterState.Unchecked
            )
        }
    }
}