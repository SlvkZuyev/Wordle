package com.slvkdev.wordle.utils

import com.slvkdev.wordle.common.LetterState


object CompareWords {
    operator fun invoke(insertedWord: String, requestedWord: String): List<LetterState>{
        val insertedLettersList = insertedWord.toMutableList()
        val requestedLettersList = requestedWord.toMutableList()
        val resultList : MutableList<LetterState> = mutableListOf()

        for((ind, letter) in insertedLettersList.withIndex()){
            if(letter == requestedWord[ind]){
                resultList.add(LetterState.Correct)
                requestedLettersList[ind] = '*'
            } else if (requestedWord.contains(letter)){
                resultList.add(LetterState.Exist)
                requestedLettersList[ind] = '*'
            } else{
                resultList.add(LetterState.Wrong)
            }
        }

        return resultList
    }
}