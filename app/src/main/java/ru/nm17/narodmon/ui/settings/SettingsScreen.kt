package ru.nm17.narodmon.ui.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.nm17.narodmon.R
import ru.nm17.narodmon.ui.elements.SettingsItem
import ru.nm17.narodmon.ui.theme.NarodMonTheme

@Composable
fun SettingsScreen(navController: NavController) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            SettingsItem(titleId = R.string.debug_menu) {
                navController.navigate(Settings.Debug.route)
            }
        }
        item {
            SettingsItem(R.string.about_app) {
                navController.navigate(Settings.AboutApp.route)
            }
        }

    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PreviewSettingsScreen() {
    NarodMonTheme {
        SettingsScreen(rememberNavController())
    }
}