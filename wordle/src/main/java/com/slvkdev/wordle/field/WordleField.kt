package com.slvkdev.wordle.field

import android.content.Context
import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.slvkdev.wordle.common.LetterState
import com.slvkdev.wordle.field.models.CheckResult
import com.slvkdev.wordle.field.models.FieldSquare
import com.slvkdev.wordle.field.models.WordleFieldStyle
import com.slvkdev.wordle.utils.CompareWords
import com.slvkdev.wordle.keyboard.areAllCorrect
import com.slvkdev.wordle.snapshot_helper.SnapshotHelper
import com.slvkdev.wordle.snapshot_helper.WordleSnapshot
import kotlinx.coroutines.CoroutineScope

class WordleFieldState(
    val requestedWord: String,
    var onWin: () -> Unit = {},
    var onLose: () -> Unit = {},
    val onInvalidWord: () -> Unit = {},
    val wordValidator: suspend (String) -> Boolean = { true },
    val insertedWords: List<String> = emptyList(),
    val shouldRestorePreviousState: Boolean = true,
    val scope: CoroutineScope,
    context: Context
) {
    var inputCheckIsInProgress = false

    private val snapshotHelper = SnapshotHelper(context)
    var delayBetweenLettersReveal = 100L
    private val fieldGrid = WordleFieldGrid()

    fun loadFromSnapshot(snapshot: WordleSnapshot) {
        fieldGrid.loadFromSnapshot(snapshot)
    }


    fun getSnapshot(): WordleSnapshot {
        return WordleSnapshot(
            requestedWord = requestedWord,
            insertedWords = fieldGrid.getInsertedWords()
        )
    }

    fun addLetter(letter: String) {
        fieldGrid.addLetter(letter)
    }

    fun moveToNextRow() {
        fieldGrid.moveToNextRow()
    }

    fun removeLastLetter() {
        fieldGrid.removeLastLetter()
    }

    suspend fun checkInput(): CheckResult {
        inputCheckIsInProgress = true
        val inputWord = fieldGrid.getCurrentInput()
        if (!inputIsValid(inputWord) || !wordValidator(inputWord)) {
            inputCheckIsInProgress = false
            return CheckResult.InputIsInvalid
        }

        val states =
            CompareWords(insertedWord = inputWord, requestedWord = requestedWord)

        fieldGrid.applyStatesToRowWithDelay(states, delayBetweenLettersReveal)

        if (states.areAllCorrect()) {
            onWin()
        }

        if (fieldGrid.currentRowIsTheLast()) {
            onLose()
        }
        inputCheckIsInProgress = false
        return CheckResult.Success(fieldGrid.getCurrentRow())
    }

    fun getRows(): List<List<FieldSquare>> {
        return fieldGrid.grid
    }

    private fun inputIsValid(input: String): Boolean {
        return input.length == 5
    }

}

@Composable
fun rememberWordleFieldState(
    requestedWord: String = "",
    onWin: () -> Unit = {},
    onLose: () -> Unit = {},
    onInvalidWord: () -> Unit = {},
    insertedWords: List<String> = emptyList(),
    wordValidator: suspend (String) -> Boolean = { true },
    scope: CoroutineScope = rememberCoroutineScope(),
    context: Context = LocalContext.current
) = remember(
    requestedWord,
    onWin,
    onLose,
    onInvalidWord,
    insertedWords,
    context,
    scope
) {
    WordleFieldState(
        requestedWord = requestedWord,
        onWin = onWin,
        onLose = onLose,
        wordValidator = wordValidator,
        onInvalidWord = onInvalidWord,
        insertedWords = insertedWords,
        scope = scope,
        context = context
    )
}

@Composable
fun WordleField(
    modifier: Modifier = Modifier,
    wordleFieldState: WordleFieldState,
    style: WordleFieldStyle = WordleFieldStyle()
) {
    Log.d("RecomposeNotificator", "Wordle Field Drown")
    LaunchedEffect(key1 = wordleFieldState.requestedWord) {
        wordleFieldState.delayBetweenLettersReveal = style.delayBetweenLettersReveal
    }

    BoxWithConstraints(modifier = modifier, contentAlignment = Alignment.Center) {

        val fieldDimensions =
            calculateWordleFieldDimensions(maxHeight = maxHeight, maxWidth = maxWidth)

        Column(
            modifier = Modifier
                .width(fieldDimensions.fieldWidth)
                .height(fieldDimensions.fieldHeight),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            for (row in wordleFieldState.getRows()) {
                WordleFieldRow(squares = row, style = style, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun WordleFieldRow(
    modifier: Modifier = Modifier,
    squares: List<FieldSquare>,
    style: WordleFieldStyle
) {

    Row(modifier = modifier) {
        for (square in squares) {
            WordleFieldSquare(
                square = square,
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxHeight()
                    .aspectRatio(0.95f, true)
                    .fillMaxWidth(),
                style = style
            )
        }
    }
}

@Composable
fun WordleFieldSquare(modifier: Modifier = Modifier, square: FieldSquare, style: WordleFieldStyle) {
    var rotationAngle by remember { mutableStateOf(0f) }
    val rotation = remember { Animatable(rotationAngle) }

    val textColor = if (rotationAngle > -90) Color.Black else Color.White
    val surfaceColor =
        if (rotationAngle > -90) style.indicationColors.uncheckedLetterColor else style.indicationColors.getColorFor(
            square.state
        )
    val borderColor = style.getBorderColorFor(square)

    LaunchedEffect(key1 = square.state) {
        if (square.state != LetterState.Unchecked) {
            rotation.animateTo(
                targetValue = -180f,
                animationSpec = tween(
                    durationMillis = style.letterRevealDuration,
                    easing = FastOutSlowInEasing
                )
            ) {
                rotationAngle = value
            }
        }
    }

    Surface(color = surfaceColor, modifier = modifier.graphicsLayer {
        rotationX = rotationAngle
        cameraDistance = 12f * density
    }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(width = 2.dp, color = borderColor)
                .graphicsLayer {
                    if (rotationAngle < -90) {
                        rotationX = -180f
                    }
                }, contentAlignment = Alignment.Center
        ) {
            Text(text = square.letter, style = style.letterStyle, color = textColor)
        }

    }

}