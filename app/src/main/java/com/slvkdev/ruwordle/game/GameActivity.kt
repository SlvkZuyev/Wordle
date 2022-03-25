package com.slvkdev.ruwordle.game

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.slvkdev.ruwordle.composables.RemainingTimeDialogue
import com.slvkdev.ruwordle.composables.TopBar
import com.slvkdev.ruwordle.ui.theme.*
import com.slvkdev.wordle.field.WordleField
import com.slvkdev.wordle.field.rememberWordleFieldState
import com.slvkdev.wordle.keyboard.calculateWordleKeyboardDimensions
import com.slvkdev.wordle.keyboard.WordleKeyboard

@Composable
fun GameScreen(navigateToRules: () -> Unit) {
    val viewModel: GameViewModel = hiltViewModel()

    GameScreen(
        viewModel = viewModel,
        onClickRules = { navigateToRules() },
        wordValidator = { word ->
            viewModel.validateWord(word)
        },
        onAttemptFinished = {
            viewModel.onAttemptFinished()
        })
}

@Composable
fun GameScreen(
    viewModel: GameViewModel,
    onClickRules: () -> Unit = {},
    wordValidator: suspend (String) -> Boolean,
    onAttemptFinished: () -> Unit = {}
) {
    val secondsUntilNextTry by viewModel.secondsUntilNextTry.collectAsState()
    val requestedWord by viewModel.wordOfTheDay.collectAsState()

    Log.d("RecomposeNotificator", "Game screen drown: $requestedWord")

    val wordleFieldState = rememberWordleFieldState(
        requestedWord = requestedWord,
        wordValidator = wordValidator,
    )

    val state = rememberWordleScreenState(
        wordleFieldState = wordleFieldState,
        onWin = {
            onAttemptFinished()
        },
        onLose = {
            onAttemptFinished()
        }
    )

    val keyboardDimensions = calculateWordleKeyboardDimensions()

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(onClickRules = onClickRules)
        Spacer(Modifier.height(16.dp))

        Box(
            Modifier
                .weight(1f)
                .fillMaxHeight(0.75f)
                .fillMaxWidth(), contentAlignment = Alignment.Center
        ) {
            WordleField(
                wordleFieldState = state.wordleFieldState,
                modifier = Modifier
                    .fillMaxWidth(0.88f)
                    .fillMaxHeight(0.9f),
                style = wordleFieldStyle
            )
        }

        Box(
            modifier = Modifier
                .width(keyboardDimensions.keyboardWidth)
                .height(keyboardDimensions.keyboardHeight),
            contentAlignment = Alignment.BottomCenter
        ) {
            WordleKeyboard(
                state = state.keyboardState,
                onKeyClick = { state.onKeyClick(it) },
                isClickable = secondsUntilNextTry <= 0,
                onClickBackspace = { state.onClickBackspace() },
                style = wordleKeyboardStyle,
                onClickApply = { state.onClickApply() }
            )

            RemainingTimeDialogue(
                modifier = Modifier.fillMaxWidth(),
                secondsUntilNewWord = secondsUntilNextTry,
                onFinish = {viewModel.refreshData()}
            )
        }


    }
    SnackbarHost(hostState = state.scaffoldState.snackbarHostState)
}


