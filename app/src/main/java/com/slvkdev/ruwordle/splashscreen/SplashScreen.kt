package com.slvkdev.ruwordle.splashscreen

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.slvkdev.ruwordle.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onLoaded: ()->Unit, durationMillis: Long = 1500) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Image(painter = painterResource(id = R.mipmap.ic_launcher_foreground), contentDescription = "Logo")
    }

    LaunchedEffect(key1 = true){
        delay(durationMillis)
        onLoaded()
    }
}