package com.slvkdev.ruwordle.game

import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slvkdev.spellingchecker.SpellingChecker
import com.slvkdev.ruwordle.data.DailyWordRepository
import com.slvkdev.ruwordle.data.UserInfoRepository
import com.slvkdev.ruwordle.utils.GetSecondsUntilMidnight
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val wordRepository: DailyWordRepository,
    private val userInfoRepository: UserInfoRepository,
    private val spellingChecker: SpellingChecker
) : ViewModel() {

    private val _wordOfTheDay = MutableStateFlow("")
    val wordOfTheDay: StateFlow<String> = _wordOfTheDay.asStateFlow()

    private val _secondsUntilNextTry = MutableStateFlow<Long>(0)
    val secondsUntilNextTry: StateFlow<Long> = _secondsUntilNextTry.asStateFlow()

    init {
        refreshData()
    }

    suspend fun validateWord(word: String): Boolean {
        return spellingChecker.isWordExist(word)
    }

    private suspend fun loadSecondsUntilNextTry() {
        userInfoRepository.getLastAttemptTimestamp().collectLatest { timestamp ->
                Log.d("SlavkLog", "New timestamp collected: $timestamp")
            if (DateUtils.isToday(timestamp)) {
                _secondsUntilNextTry.value = GetSecondsUntilMidnight()
            } else {
                _secondsUntilNextTry.value = 0
            }
        }
    }

    private fun loadWordOfTheDay() {
        _wordOfTheDay.value = wordRepository.getDailyWord()
    }

    fun refreshData() {
        viewModelScope.launch {
            loadSecondsUntilNextTry()
        }
        loadWordOfTheDay()
    }

    fun onAttemptFinished() {
        Log.d("SlvkLog", "Attempt Finished")
        CoroutineScope(Dispatchers.IO).launch {
            updateLastAttemptTimestamp()
        }
    }

    private suspend fun updateLastAttemptTimestamp() {
        userInfoRepository.saveLastAttemptTimestamp(System.currentTimeMillis())
    }
}