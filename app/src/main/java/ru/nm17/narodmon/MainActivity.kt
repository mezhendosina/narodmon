@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package ru.nm17.narodmon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.nm17.narodmon.db.AppDatabase
import ru.nm17.narodmon.db.entities.KVSetting
import ru.nm17.narodmon.ui.dialogs.AgreementDialog
import ru.nm17.narodmon.ui.navHost.AppNavHost
import ru.nm17.narodmon.ui.navHost.MainScreen
import ru.nm17.narodmon.ui.sensorsScreen.SensorsScreen
import ru.nm17.narodmon.ui.theme.NarodMonTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "data"
        ).build()

//        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
//
//        val sharedPreferences = EncryptedSharedPreferences.create(
//            "secret_shared_prefs",
//            masterKeyAlias,
//            createDeviceProtectedStorageContext(),
//            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
//            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
//        )

        // use the shared preferences and editor as you normally would

        // use the shared preferences and editor as you normally would
//        val credSharedPreferences = sharedPreferences


        setContent {
            val coScope = rememberCoroutineScope()

            //var asd = getPreferences()

            NarodMonTheme {
                var agreed by remember { mutableStateOf(true) }

                LaunchedEffect(key1 = Unit, block = {
                    coScope.launch(Dispatchers.IO) {
                        if (db.kvDao().getByKey("agreement_accepted")?.value != "true") {
                            agreed = false
                        }
                    }
                })

                if (!agreed) {
                    Scaffold {
                        AgreementDialog {
                            coScope.launch(Dispatchers.IO) {
                                db.kvDao().setAll(KVSetting("agreement_accepted", "true"))
                                agreed = true
                            }
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it)
                        ) {
                            CircularProgressIndicator()
                            Text(text = stringResource(R.string.waiting_for_user_agreement))
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
fun NavHolderEl() {
    //NavHost(navController = NavHostController(N), graph =)
}
