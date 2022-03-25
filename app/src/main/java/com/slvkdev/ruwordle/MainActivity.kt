package com.slvkdev.ruwordle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.slvkdev.ruwordle.game.GameScreen
import com.slvkdev.ruwordle.navigation.Screen
import com.slvkdev.ruwordle.rules.RulesScreen
import com.slvkdev.ruwordle.splashscreen.SplashScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WordleApp()
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WordleApp() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = Screen.Splash.route,
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Down,
                    animationSpec = tween(700)
                )
            }
        ) {
            SplashScreen(
                onLoaded = {
                    navController.navigate(Screen.Game.route) {
                        popUpTo(Screen.Splash.route) {
                            inclusive = true
                        }
                    }
                },
                durationMillis = 1200
            )
        }

        composable(
            route = Screen.Game.route,
            ) {
            GameScreen(
                navigateToRules = {
                    navController.navigate(Screen.Rules.route) {

                    }
                })
        }

        composable(
            route = Screen.Rules.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(400)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(400)
                )
            }) {
            RulesScreen(navigateToGame = {
                navController.popBackStack()
            })
        }

    }
}
