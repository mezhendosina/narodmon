package ru.nm17.narodmon.ui.navHost

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.nm17.narodmon.ui.sensorsScreen.SensorsScreen
import ru.nm17.narodmon.ui.theme.NarodMonTheme
import ru.nm17.narodmon.ui.webCamsScreen.WebCamsScreen


val items = listOf(
    MainScreenSealed.Sensors,
    MainScreenSealed.Webcams,
    MainScreenSealed.Messages
)

@Composable
fun MainScreen(outerNavController: NavController) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {

                items.forEach { screen ->
                    NavigationBarItem(
                        selected = navController.currentDestination?.route == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        label = { Text(text = stringResource(id = screen.resourceId)) },
                        icon = {
                            Icon(
                                painter = painterResource(id = screen.iconId),
                                contentDescription = ""
                            )
                        })
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(
            navController,
            startDestination = MainScreenSealed.Sensors.route,
            Modifier.padding(it)
        ) {
            composable(MainScreenSealed.Sensors.route) {
                SensorsScreen(navController) {
                    outerNavController.navigate("settings")
                }
            }
            composable(MainScreenSealed.Webcams.route) { WebCamsScreen(navController) }
            composable(MainScreenSealed.Messages.route) { }
        }
    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    NarodMonTheme {
        MainScreen(rememberNavController())
    }
}