@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package ru.nm17.narodmon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import kotlinx.coroutines.launch
import ru.nm17.narodmon.db.AppDatabase
import ru.nm17.narodmon.ui.theme.NarodMonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()


        setContent {
            val navController = rememberNavController()
            //var asd = getPreferences()

            NarodMonTheme {
                NavHost(navController = navController, startDestination = "agreement") {
                    composable("agreement") {  }
                    composable("friendslist") {  }
                    /*...*/
                }

                // A surface container using the 'background' color from the theme


                Scaf({ Text("Content", modifier = Modifier.padding(it)) })

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun NavHolderEl() {
    //NavHost(navController = NavHostController(N), graph =)
}

@Composable
fun Scaf(content: @Composable (PaddingValues) -> Unit) {
    var expanded = rememberDrawerState(initialValue = DrawerValue.Closed)
    var coScope = rememberCoroutineScope();

    /*DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(
            text = { Text("Refresh") },
            onClick = { /* Handle refresh! */ }
        )
        DropdownMenuItem(
            text = { Text("Settings") },
            onClick = { /* Handle settings! */ }
        )
        Divider()
        DropdownMenuItem(
            text = { Text("Send Feedback") },
            onClick = { /* Handle send feedback! */ }
        )
    }*/
    ModalNavigationDrawer(drawerState = expanded, drawerContent = {
        ModalDrawerSheet {
            Text("Drawer title", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleLarge)
            Divider()
            NavigationDrawerItem(
                label = { Text(text = "Drawer Item") },
                selected = true,
                onClick = { /*TODO*/ }
            )
            // ...other drawer items
        }

    }) {
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NarodMonTheme {
        Greeting("Android")
    }
}