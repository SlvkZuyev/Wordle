package com.slvkdev.ruwordle.rules

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.slvkdev.ruwordle.R
import com.slvkdev.ruwordle.ui.theme.indicationColors
import com.slvkdev.ruwordle.ui.theme.wordleFieldStyle
import com.slvkdev.wordle.common.LetterState
import com.slvkdev.wordle.field.WordleFieldRow
import com.slvkdev.wordle.field.models.FieldSquare

@Composable
fun RulesScreen(navigateToGame: () -> Unit = {}) {
    Log.d("SlvkLog", "Rules Screen Drown!!")
    LazyColumn(
        Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        item {
            RulesContent(navigateToGame)
        }
    }
}

@Composable
fun ButtonPlay(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = indicationColors.correctLetterColor)
    ) {
        Text(
            text = "ПОЛЕТЕЛИ!",
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            color = Color.White
        )
    }
}

@Composable
fun RulesContent(onClickPlay: ()->Unit) {
    Text(
        text = "ПРАВИЛА ИГРЫ",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h2
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = stringResource(id = R.string.rules_paragraph_1),
        style = MaterialTheme.typography.body1
    )

    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = stringResource(id = R.string.rules_paragraph_2),
        style = MaterialTheme.typography.body1
    )

    Divider(Modifier.padding(vertical = 16.dp))

    Text(
        text = stringResource(id = R.string.rules_paragraph_3),
        style = MaterialTheme.typography.body1
    )
    ExampleWord_1()

    Divider(Modifier.padding(vertical = 16.dp))
    Text(
        text = stringResource(id = R.string.rules_paragraph_4),
        style = MaterialTheme.typography.body1
    )
    ExampleWord_2()
    Text(
        text = stringResource(id = R.string.rules_paragraph_5),
        style = MaterialTheme.typography.body1
    )
    Divider(Modifier.padding(vertical = 16.dp))

    Text(
        text = stringResource(id = R.string.rules_paragraph_6),
        style = MaterialTheme.typography.body1
    )
    ExampleWord_3()
    Divider(Modifier.padding(vertical = 16.dp))

    Text(
        text = stringResource(id = R.string.rules_paragraph_7),
        style = MaterialTheme.typography.body1
    )
    ExampleWord_4()
    Divider(Modifier.padding(vertical = 16.dp))
    Box(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp, top = 12.dp), contentAlignment = Alignment.BottomEnd
    ) {
        ButtonPlay() {
            onClickPlay()
        }
    }

}

@Composable
fun ExampleWord_1() {
    WordleFieldRow(
        modifier = Modifier
            .height(70.dp)
            .padding(vertical = 8.dp),
        squares = listOf(
            FieldSquare(
                initialLetter = "р",
                initialState = LetterState.Wrong
            ),
            FieldSquare(
                initialLetter = "е",
                initialState = LetterState.Wrong
            ),
            FieldSquare(
                initialLetter = "б",
                initialState = LetterState.Wrong
            ),
            FieldSquare(
                initialLetter = "у",
                initialState = LetterState.Wrong
            ),
            FieldSquare(
                initialLetter = "с",
                initialState = LetterState.Exist
            ),

            ), style = wordleFieldStyle
    )
}

@Composable
fun ExampleWord_2() {
    WordleFieldRow(
        modifier = Modifier
            .height(70.dp)
            .padding(vertical = 8.dp),
        squares = listOf(
            FieldSquare(
                initialLetter = "с",
                initialState = LetterState.Wrong
            ),
            FieldSquare(
                initialLetter = "о",
                initialState = LetterState.Correct
            ),
            FieldSquare(
                initialLetter = "с",
                initialState = LetterState.Correct
            ),
            FieldSquare(
                initialLetter = "н",
                initialState = LetterState.Wrong
            ),
            FieldSquare(
                initialLetter = "а",
                initialState = LetterState.Wrong
            ),

            ), style = wordleFieldStyle
    )
}

@Composable
fun ExampleWord_3() {
    WordleFieldRow(
        modifier = Modifier
            .height(70.dp)
            .padding(vertical = 8.dp),
        squares = listOf(
            FieldSquare(
                initialLetter = "г",
                initialState = LetterState.Correct
            ),
            FieldSquare(
                initialLetter = "о",
                initialState = LetterState.Correct
            ),
            FieldSquare(
                initialLetter = "с",
                initialState = LetterState.Correct
            ),
            FieldSquare(
                initialLetter = "т",
                initialState = LetterState.Correct
            ),
            FieldSquare(
                initialLetter = "ь",
                initialState = LetterState.Correct
            ),

            ), style = wordleFieldStyle
    )
}

@Composable
fun ExampleWord_4() {
    WordleFieldRow(
        modifier = Modifier
            .height(70.dp)
            .padding(vertical = 8.dp),
        squares = listOf(
            FieldSquare(
                initialLetter = "п",
                initialState = LetterState.Wrong
            ),
            FieldSquare(
                initialLetter = "и",
                initialState = LetterState.Wrong
            ),
            FieldSquare(
                initialLetter = "р",
                initialState = LetterState.Wrong
            ),
            FieldSquare(
                initialLetter = "а",
                initialState = LetterState.Wrong
            ),
            FieldSquare(
                initialLetter = "т",
                initialState = LetterState.Wrong
            ),

            ), style = wordleFieldStyle
    )
}