package com.slvkdev.ruwordle.navigation

sealed class Screen(val route: String) {
    object Splash: Screen("splash")
    object Game: Screen("game")
    object Rules: Screen("rules")
}
