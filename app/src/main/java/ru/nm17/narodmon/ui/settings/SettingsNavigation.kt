package ru.nm17.narodmon.ui.settings

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsNavigation() {
    val navController = rememberNavController()
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = navController.currentDestination?.route ?: "") })
    }) {
        NavHost(
            navController = navController,
            startDestination = Settings.Main.route,
            modifier = Modifier.padding(it)
        ) {
            composable(Settings.Main.route) { SettingsScreen(navController) }
            composable(Settings.AboutApp.route) { }
            composable(Settings.Debug.route) { }
        }

    }
}

@Preview
@Composable
fun PreviewSettingsNavigation() {
    SettingsNavigation()
}