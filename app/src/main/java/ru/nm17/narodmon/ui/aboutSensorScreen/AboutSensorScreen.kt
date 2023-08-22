package ru.nm17.narodmon.ui.aboutSensorScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.nm17.narodmon.ui.entities.AboutSensorUiEntity
import ru.nm17.narodmon.ui.theme.NarodMonTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutSensorScreen(aboutSensorUiEntity: AboutSensorUiEntity) {
    Scaffold(topBar = {
        LargeTopAppBar(title = { Text(aboutSensorUiEntity.name) })
    }) {
        Column(Modifier.padding(it)) {
            
        }
    }
}

@Preview
@Composable
fun AboutSensorScreenPreview() {
    NarodMonTheme {
        AboutSensorScreen(aboutSensorUiEntity = AboutSensorUiEntity("123", emptyList()))
    }
}
