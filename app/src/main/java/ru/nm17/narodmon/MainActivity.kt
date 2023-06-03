@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package ru.nm17.narodmon

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import ru.nm17.narodmon.db.AppDatabase
import ru.nm17.narodmon.db.entities.KVSetting
import ru.nm17.narodmon.ui.elements.AgreementDialog
import ru.nm17.narodmon.ui.elements.GenericNavScaffold
import ru.nm17.narodmon.ui.theme.NarodMonTheme

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val coScope = rememberCoroutineScope()



    NavHost(navController = navController, startDestination = "sensors") {
        composable("agreement") {

        }
        composable("sensors") {

            SensorsPage(navController)
        }

        /*...*/
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "data"
        ).build()





        setContent {
            val coScope = rememberCoroutineScope()





            //var asd = getPreferences()

            NarodMonTheme {
                var agreed by remember { mutableStateOf(true) }

                LaunchedEffect(key1 = "first_agreement_check", block = {
                    coScope.launch(Dispatchers.IO) {
                        if (db.kvDao().getByKey("agreement_accepted")?.value != "true") {
                            agreed = false
                        }
                    }
                })

                if (!agreed) {
                    AgreementDialog {
                        coScope.launch(Dispatchers.IO) {
                            db.kvDao().setAll(KVSetting("agreement_accepted", "true"))
                            agreed = true
                        }
                    }
                }

                if (!agreed) {
                    Surface {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            CircularProgressIndicator()
                            Text(text = "вы не должны видеть этот текст")
                        }
                    }

                } else {
                    AppNavHost()
                }



                // A surface container using the 'background' color from the theme




            }
        }
    }
}

@Composable
fun SensorsPage(navController: NavController) {
    GenericNavScaffold(navDrawerSheet = {ModalDrawerSheet {
        Text("Drawer title", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleLarge)
        Divider()
        NavigationDrawerItem(
            label = { Text(text = "Drawer Item") },
            selected = true,
            onClick = { /*TODO*/ }
        )
        // ...other drawer items
    }}) {
        Greeting(name = "Hello world", modifier = Modifier.padding(it))
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



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NarodMonTheme {
        Greeting("Android")
    }
}