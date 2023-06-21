package ru.nm17.narodmon.ui.navHost

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.nm17.narodmon.ui.settings.SettingsScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(navController)
        }
        composable("settings") {
            SettingsScreen()
        }
    }
}

@Preview
@Composable
fun PreviewAppNavHost() {
    AppNavHost()
}
