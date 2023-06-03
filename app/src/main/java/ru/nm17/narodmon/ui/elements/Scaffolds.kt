@file:OptIn(ExperimentalMaterial3Api::class)

package ru.nm17.narodmon.ui.elements

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

/**
 * Простой скаффолд с навигацией.
 *
 * TODO: Использовать для датчиков
 */
@Composable
fun GenericNavScaffold(navDrawerSheet: @Composable () -> Unit, content: @Composable (PaddingValues) -> Unit) {
    var expanded = rememberDrawerState(initialValue = DrawerValue.Closed)
    var coScope = rememberCoroutineScope();

    ModalNavigationDrawer(drawerState = expanded, drawerContent = navDrawerSheet) {
        Scaffold(
                topBar = {
                    TopAppBar(
                            title = { Text("Top App Bar") },
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