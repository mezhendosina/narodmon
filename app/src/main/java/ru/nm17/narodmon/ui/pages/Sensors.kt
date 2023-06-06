package ru.nm17.narodmon.ui.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.nm17.narodmon.R
import ru.nm17.narodmon.ui.elements.GenericNavScaffold
import ru.nm17.narodmon.ui.elements.TileMap

data class SensorFilter(
    val stringRes: Int,
    val code: Int,
    var enabled: MutableState<Boolean> = mutableStateOf(false),
)

@ExperimentalMaterial3Api
@Composable
fun SensorsPage(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    var searchActive by remember { mutableStateOf(false) }

    var filterShown by remember { mutableStateOf(false) }
    var filterMine by remember { mutableStateOf(false) }

    val filterItems = remember {
        listOf(
            /* TODO:
             *  Заменить `code` на настоящее значение
             *  либо динамически его подгружать из ответа АПИ
             *  (см. /appInit, ключ в жсоне: types.type) */
            SensorFilter(R.string.filter_temp, 0),
            SensorFilter(R.string.filter_temp_water, 1),
            SensorFilter(R.string.filter_temp_ground, 2),
            SensorFilter(R.string.filter_temp_dew_point, 3),
            SensorFilter(R.string.filter_humidity, 4),
            SensorFilter(R.string.filter_pressure, 5),
            SensorFilter(R.string.filter_lightness, 6),
            SensorFilter(R.string.filter_uv, 7),
            SensorFilter(R.string.filter_radiation, 8),
            SensorFilter(R.string.filter_rainfall, 9),
            SensorFilter(R.string.filter_dust, 10),
            SensorFilter(R.string.filter_wind_speed, 11),
            SensorFilter(R.string.filter_wind_direction, 12),
            SensorFilter(R.string.filter_concentration, 13),
            SensorFilter(R.string.filter_power, 14),
            SensorFilter(R.string.filter_voltage, 15),
            SensorFilter(R.string.filter_amperage, 16),
            SensorFilter(R.string.filter_energy, 17),
            SensorFilter(R.string.filter_battery, 18),
            SensorFilter(R.string.filter_rxtx, 19),
            SensorFilter(R.string.filter_signal, 20),
            SensorFilter(R.string.filter_water_meter, 21),
            SensorFilter(R.string.filter_time, 22),
        )
    }

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
                modifier = Modifier.fillMaxWidth()
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
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = stringResource(R.string.sensors_filter_title),
                    fontSize = 24.sp,
                    fontWeight = FontWeight(500),
                )
            }

            LazyColumn(
                modifier = Modifier.padding(horizontal = 4.dp),
            ) {
                items(filterItems) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Checkbox(
                            checked = it.enabled.value,
                            onCheckedChange = { checked ->
                                it.enabled.value = checked
                            },
                        )
                        Text(
                            text = stringResource(id = it.stringRes),
                            modifier = Modifier.clickable {
                                it.enabled.value = !it.enabled.value
                            }
                        )
                    }
                }
            }
        }
    }
}