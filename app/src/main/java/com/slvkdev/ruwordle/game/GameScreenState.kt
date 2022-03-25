package com.slvkdev.ruwordle.game

import android.content.Context
import android.util.Log
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.toUpperCase
import com.slvkdev.wordle.field.WordleFieldState
import com.slvkdev.wordle.field.models.CheckResult
import com.slvkdev.wordle.field.models.FieldSquare
import com.slvkdev.wordle.field.models.areAllCorrect
import com.slvkdev.wordle.field.rememberWordleFieldState
import com.slvkdev.wordle.keyboard.WordleKeyboardState
import com.slvkdev.wordle.keyboard.rememberWordleKeyboardState
import com.slvkdev.wordle.snapshot_helper.SnapshotHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

class WordleScreenState(
    val scaffoldState: ScaffoldState,
    val coroutineScope: CoroutineScope,
    val keyboardState: WordleKeyboardState,
    val wordleFieldState: WordleFieldState,
    val onWin: () -> Unit,
    val onLose: () -> Unit,
    val context: Context,
) {
    private val snapShotHelper = SnapshotHelper(context)

    init {
        Log.d("SlvkLog", "Wordle Screen State Created")
        setOnWinBehaviour()
        setOnLoseBehaviour()
        if (wordleFieldState.requestedWord.isNotEmpty()) {
            loadFromSnapshot()
        }
    }

    private fun loadFromSnapshot() {
        coroutineScope.launch {
            val todaySnapshot = snapShotHelper.loadTodaySnapshot()
            if (todaySnapshot != null) {
                keyboardState.loadFromSnapshot(todaySnapshot)
                wordleFieldState.loadFromSnapshot(todaySnapshot)
            }
        }
    }

    private fun setOnWinBehaviour() {
        wordleFieldState.onWin = {
            isGameInProgress = false
            coroutineScope.launch {
                showSnackBarMessage(
                    "Поздравляю с победой, товарищ!",
                    duration = SnackbarDuration.Indefinite
                )
            }
        }
    }

    private fun setOnLoseBehaviour() {
        wordleFieldState.onLose = {
            isGameInProgress = false
            onLose()
            coroutineScope.launch {
                showSnackBarMessage(
                    "Загаданое слово было: \"${wordleFieldState.requestedWord.uppercase(Locale.getDefault())}...\"",
                    duration = SnackbarDuration.Indefinite
                )
            }
        }
    }

    var isGameInProgress = true

    fun onKeyClick(letter: String) {
        if (isGameInProgress) {
            wordleFieldState.addLetter(letter)
        }
    }

    fun onClickBackspace() {
        if (isGameInProgress && !wordleFieldState.inputCheckIsInProgress) {
            wordleFieldState.removeLastLetter()
        }
    }

    private suspend fun showSnackBarMessage(
        message: String,
        duration: SnackbarDuration = SnackbarDuration.Short
    ) {
        scaffoldState.snackbarHostState.showSnackbar(message, duration = duration)
    }

    private suspend fun updateSnapshot() {
        val snapshot = wordleFieldState.getSnapshot()
        snapShotHelper.saveSnapshot(snapshot)
    }

    fun onClickApply() {
        if (!isGameInProgress || wordleFieldState.inputCheckIsInProgress) {
            return
        }

        coroutineScope.launch {
            val checkResult = wordleFieldState.checkInput()

            when (checkResult) {
                CheckResult.InputIsInvalid -> {
                    onInvalidInput()
                }

                is CheckResult.Success -> {
                    onSuccessInput(checkResult)
                }
            }
        }
    }

    private fun onInvalidInput() {
        coroutineScope.launch {
            showSnackBarMessage("С этим словом что-то не так...")
        }
    }

    private fun onSuccessInput(checkResult: CheckResult.Success) {
        updateKeyboardKeysStates(checkResult.squares)

        if (checkResult.squares.areAllCorrect()) {
            isGameInProgress = false
            onWin()
        }
        wordleFieldState.moveToNextRow()
        coroutineScope.launch {
            updateSnapshot()
        }
    }

    private fun updateKeyboardKeysStates(squares: List<FieldSquare>) {
        for (square in squares) {
            keyboardState.changeKeyState(
                keyLetter = square.letter,
                state = square.state
            )
        }
    }
}

@Composable
fun rememberWordleScreenState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    keyboardState: WordleKeyboardState = rememberWordleKeyboardState(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    wordleFieldState: WordleFieldState = rememberWordleFieldState(),
    onWin: () -> Unit,
    onLose: () -> Unit,
    context: Context = LocalContext.current
) = remember(wordleFieldState) {
    //scaffoldState, coroutineScope, keyboardState,
    WordleScreenState(
        scaffoldState = scaffoldState,
        coroutineScope = coroutineScope,
        keyboardState = keyboardState,
        wordleFieldState = wordleFieldState,
        onWin = onWin,
        onLose = onLose,
        context = context,
    )
}
