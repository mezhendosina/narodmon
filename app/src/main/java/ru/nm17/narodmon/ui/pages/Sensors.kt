package ru.nm17.narodmon.ui.pages

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.nm17.narodmon.Greeting
import ru.nm17.narodmon.R
import ru.nm17.narodmon.ui.elements.GenericNavScaffold

@ExperimentalMaterial3Api
@Composable
fun SensorsPage(navController: NavController) {
    GenericNavScaffold(
        title = { Text(text = stringResource(R.string.sensors_page_title))}
    ) {
        Greeting(name = "world", modifier = Modifier.padding(it))
    }
}