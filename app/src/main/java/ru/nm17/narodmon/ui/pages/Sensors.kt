package ru.nm17.narodmon.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.nm17.narodmon.R
import ru.nm17.narodmon.ui.elements.GenericNavScaffold
import ru.nm17.narodmon.ui.elements.TileMap

enum class SensorsFilter {
    All, Thermometer, Camera,
}

@ExperimentalMaterial3Api
@Composable
fun SensorsPage(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    var searchActive by remember { mutableStateOf(false) }

    var filterShown by remember { mutableStateOf(false) }
    var filterMine by remember { mutableStateOf(false) }

    val filterItems = listOf<String>(
        stringResource(R.string.filter_temp),
        stringResource(R.string.filter_temp_water),
        stringResource(R.string.filter_temp_ground),
        stringResource(R.string.filter_temp_dew_point),
        stringResource(R.string.filter_humidity),
        stringResource(R.string.filter_pressure),
        stringResource(R.string.filter_lightness),
        stringResource(R.string.filter_uv),
        stringResource(R.string.filter_radiation),
        stringResource(R.string.filter_rainfall),
        stringResource(R.string.filter_dust),
        stringResource(R.string.filter_wind_speed),
        stringResource(R.string.filter_wind_direction),
        stringResource(R.string.filter_concentration),
        stringResource(R.string.filter_power),
        stringResource(R.string.filter_voltage),
        stringResource(R.string.filter_amperage),
        stringResource(R.string.filter_energy),
        stringResource(R.string.filter_battery),
        stringResource(R.string.filter_rxtx),
        stringResource(R.string.filter_signal),
        stringResource(R.string.filter_water_meter),
        stringResource(R.string.filter_time),
    )

    val scrConfig = LocalConfiguration.current
    val mapHeight = scrConfig.screenHeightDp / 3

    GenericNavScaffold(
        title = { Text(text = stringResource(R.string.sensors_page_title)) }
    ) {
        Column(modifier = Modifier.padding(it)) {

            TileMap(modifier = Modifier.height(mapHeight.dp))

            SearchBar(
                query = searchQuery,
                active = searchActive,
                onActiveChange = { active -> searchActive = active },
                onQueryChange = { query -> searchQuery = query },
                onSearch = { searchActive = false },
                placeholder = { Text(stringResource(R.string.search)) },
                modifier = Modifier
                    //.padding(horizontal = 8.dp)
                    .fillMaxWidth()
            ) {}

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(horizontal = 8.dp),
            ) {
                AssistChip(
                    onClick = { filterShown = true },
                    label = { Text(text = stringResource(R.string.sensors_filter)) },
                )

                AssistChip(
                    onClick = { },
                    label = { Text(text = stringResource(R.string.sensors_sorting)) },
                )

                FilterChip(
                    selected = filterMine,
                    onClick = { filterMine = !filterMine },
                    label = { Text(text = stringResource(R.string.sensors_mine)) },
                )
            }
        }
    }

    if (filterShown) {
        ModalBottomSheet(onDismissRequest = { filterShown = false }) {
            Text(text = "Hello")
        }
    }
}