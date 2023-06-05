package ru.nm17.narodmon.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import ru.nm17.narodmon.Greeting
import ru.nm17.narodmon.R
import ru.nm17.narodmon.ui.elements.GenericNavScaffold

@ExperimentalMaterial3Api
@Composable
fun SensorsPage(navController: NavController) {
    GenericNavScaffold(
        title = { Text(text = stringResource(R.string.sensors_page_title)) }
    ) {
        Column {
            Greeting("Hello sensors")
            Row {
                FilterChip(
                    selected = true,
                    onClick = { },
                    label = { Text("Temp") }
                )

                FilterChip(
                    selected = false,
                    onClick = { },
                    label = { Text("Abc") }
                )

                FilterChip(
                    selected = false,
                    onClick = { },
                    label = { Text("Def") }
                )
            }
        }
    }
}