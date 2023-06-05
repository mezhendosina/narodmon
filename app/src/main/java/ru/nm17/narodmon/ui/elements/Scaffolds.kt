@file:OptIn(ExperimentalMaterial3Api::class)

package ru.nm17.narodmon.ui.elements

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

/**
 * Простой скаффолд с навигацией.
 *
 * TODO: Использовать для датчиков
 */
@Composable
fun GenericNavScaffold(title: @Composable () -> Unit, content: @Composable (PaddingValues) -> Unit) {
    val expanded = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coScope = rememberCoroutineScope()
    val navController = rememberNavController() // TODO: Используй меня

    ModalNavigationDrawer(drawerState = expanded, drawerContent = {
        ModalDrawerSheet {
            Text("Drawer title", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleLarge)
            Divider()
            NavigationDrawerItem(
                label = { Text(text = "Drawer Item") },
                selected = true,
                onClick = { navController.navigate("sensors") }
            )
        }
    }) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = title,
                    colors = TopAppBarDefaults.largeTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    navigationIcon = {
                        IconButton(onClick = { coScope.launch { expanded.open() } }) {
                            Icon(Icons.Filled.Menu, contentDescription = null)
                        }
                    },
                )
            },
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                FloatingActionButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "fab icon")
                }
            },
            //drawerContent = { Text(text = "Drawer Menu 1") },
            content = content,
        )
    }
}