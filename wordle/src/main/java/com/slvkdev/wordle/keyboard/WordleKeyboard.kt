package com.slvkdev.wordle.keyboard

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.slvkdev.wordle.common.LetterState
import com.slvkdev.wordle.keyboard.model.Key
import com.slvkdev.wordle.keyboard.model.KeyboardStyle
import com.slvkdev.wordle.keyboard.utils.CreateKeys
import com.slvkdev.wordle.snapshot_helper.WordleSnapshot


class WordleKeyboardState() {
    private val keysInTopRow = 12
    private val keysInMiddleRow = 11
    private val keysInBottomRow = 9

    private val keys = mutableStateListOf<Key>()

    init {
        keys.addAll(CreateKeys())
    }

    val topRowKeys = keys.subList(fromIndex = 0, toIndex = keysInTopRow)
    val middleRowKeys =
        keys.subList(fromIndex = keysInTopRow, toIndex = keysInTopRow + keysInMiddleRow)
    val bottomRowKeys =
        keys.subList(
            fromIndex = keysInTopRow + keysInMiddleRow,
            toIndex = keysInTopRow + keysInMiddleRow + keysInBottomRow
        )

    fun changeKeyState(keyLetter: String, state: LetterState) {
        val foundKey = keys.find { it.text == keyLetter } ?: return
        if (foundKey.state != LetterState.Correct) {
            foundKey.state = state
        }
    }

    fun loadFromSnapshot(snapshot: WordleSnapshot){
        val keys = snapshot.convertToKeys()
        for(key in keys){
            changeKeyState(key.text, key.state)
        }
    }

    fun clear() {
        for (key in keys) {
            key.state = LetterState.Unchecked
        }
    }

    private fun notifyChanges() {
        keys.add(Key())
        keys.removeLast()
    }
}

@Composable
fun rememberWordleKeyboardState() = remember {
    WordleKeyboardState()
}

@Composable
fun WordleKeyboard(
    state: WordleKeyboardState,
    style: KeyboardStyle = KeyboardStyle(),
    isClickable: Boolean = true,
    onKeyClick: (String) -> Unit = {},
    onClickApply: () -> Unit = {},
    onClickBackspace: () -> Unit = {}
) {
    Log.d("RecomposeNotificator", "Wordle Keyboard Drown")
    val keyboardDimensions = calculateWordleKeyboardDimensions()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        val keyModifier = Modifier
            .width(keyboardDimensions.keyWidth)
            .height(keyboardDimensions.keyHeight)
            .padding(horizontal = 2.dp, vertical = 3.dp)

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            for (key in state.topRowKeys) {
                KeyboardLetterKey(
                    modifier = keyModifier,
                    key = key,
                    style = style,
                    onClick = {
                        onKeyClick(key.text)
                    },
                    isClickable = isClickable
                )
            }
        }

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            for (key in state.middleRowKeys) {
                KeyboardLetterKey(
                    modifier = keyModifier,
                    key = key,
                    style = style,
                    onClick = {
                        onKeyClick(key.text)
                    },
                    isClickable = isClickable
                )
            }
        }

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            KeyboardSpecialKey(
                modifier = Modifier
                    .padding(horizontal = 1.dp, vertical = 2.dp)
                    .height(keyboardDimensions.keyHeight)
                    .width(keyboardDimensions.specialKeyWidth),
                icon = Icons.Default.Check,
                color = style.indicationColors.uncheckedLetterColor,
                tint = style.letterStyle.color,
                onClick = {
                    onClickApply()
                },
                isClickable = isClickable
            )


            for (key in state.bottomRowKeys) {
                KeyboardLetterKey(
                    modifier = keyModifier,
                    key = key,
                    style = style,
                    onClick = {
                        onKeyClick(key.text)
                    },
                    isClickable = isClickable
                )
            }


            KeyboardSpecialKey(
                modifier = Modifier
                    .padding(horizontal = 1.dp, vertical = 2.dp)
                    .height(keyboardDimensions.keyHeight)
                    .width(keyboardDimensions.specialKeyWidth),
                icon = Icons.Default.ArrowBack,
                color = style.indicationColors.uncheckedLetterColor,
                tint = style.letterStyle.color,
                onClick = onClickBackspace,
                isClickable = isClickable
            )
        }

    }
}

@Composable
fun KeyboardLetterKey(
    modifier: Modifier,
    key: Key,
    style: KeyboardStyle,
    onClick: () -> Unit,
    isClickable: Boolean = true
) {
    Crossfade(targetState = style.indicationColors.getColorFor(key.state)) {
        Surface(
            modifier = modifier.clickable {
                if (isClickable) {
                    onClick()
                }
            },
            color = it,
            shape = RoundedCornerShape(size = 5.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 2.dp, vertical = 2.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = key.text, style = style.letterStyle)
            }
        }
    }

}

@Composable
fun KeyboardSpecialKey(
    modifier: Modifier,
    icon: ImageVector,
    color: Color,
    tint: Color,
    onClick: () -> Unit,
    isClickable: Boolean = true
) {
    Surface(modifier = modifier.clickable {
        if (isClickable) {
            onClick()
        }
    }, color = color, shape = RoundedCornerShape(size = 5.dp)) {
        Box(
            modifier = Modifier
                .padding(horizontal = 4.dp, vertical = 2.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, null, tint = tint)
        }
    }
}

