package com.slvkdev.wordle.common

/*
Unchecked - letter is unchecked
Correct - letter stays on the right place
Exist - letter exist in the requested word but stays not in the right place
Wrong - letter does not exist in requested word
 */
enum class LetterState {
    Unchecked, Correct, Exist, Wrong
}
