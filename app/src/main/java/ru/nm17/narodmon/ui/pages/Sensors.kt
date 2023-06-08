package ru.nm17.narodmon.ui.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.nm17.narodmon.R
import ru.nm17.narodmon.db.entities.SensorType
import ru.nm17.narodmon.ui.elements.GenericNavScaffold
import ru.nm17.narodmon.ui.elements.TileMap
import ru.nm17.narodmon.ui.iosevkaFamily

data class Sensor(
    // TODO: Вынести в отдельный класс, и явно не в директорию `ui`
    val id: Int,
    val type: SensorType,
    val deviceName: String,
    val deviceOwner: Int,
    val name: String,
    val favorite: Boolean,
    val public: Boolean,
    val mine: Boolean,
    val location: String,
    val distance: Double,  // километры
    val value: Double,
    val unit: String,
    val changed: Int,
)

data class SensorFilter(
    // TODO: Можно попробовать объединить с db/SensorType.kt
    val stringRes: Int,
    val code: Int,
    var enabled: MutableState<Boolean> = mutableStateOf(false),
)

data class SensorSortingItem(
    val stringRes: Int,
    val sortingType: SortingType,
)

enum class SortingType {
    DISTANCE, TYPE, UPD_TIME,
    NAME, VALUE,
}

@ExperimentalMaterial3Api
@Composable
fun SensorsPage(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    var searchActive by remember { mutableStateOf(false) }

    var sortingShow by remember { mutableStateOf(false) }
    var sortingType by remember { mutableStateOf(SortingType.DISTANCE) }
    var sortingDesc by remember { mutableStateOf(false) }
    var sortingMine by remember { mutableStateOf(false) }
    var sortingFav  by remember { mutableStateOf(false) }

    val sortingTypes = remember {
        listOf(
            SensorSortingItem(R.string.sort_distance, SortingType.DISTANCE),
            SensorSortingItem(R.string.sort_type, SortingType.TYPE),
            SensorSortingItem(R.string.sort_update_time, SortingType.UPD_TIME),
            SensorSortingItem(R.string.sort_name, SortingType.NAME),
            SensorSortingItem(R.string.sort_value, SortingType.VALUE),
        )
    }

    var filterShow by remember { mutableStateOf(false) }
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

    val sensors = remember {
        mutableListOf(
            // TODO: загружать датчики с сервера. Этот список -- для макета
            Sensor(
                0,
                SensorType(0, "temp", "C"),
                "device0", 0,
                "sensor0", favorite = true,
                public = true, mine = false,
                "Москва", 0.4,
                20.0, "C", 1686142800,
            ),
            Sensor(
                1,
                SensorType(4, "humidity", "%"),
                "device1", 0,
                "sensor1", favorite = true,
                public = false, mine = false,
                "Подмосковье", 1.1,
                39.0, "%", 1686142800,
            ),
            Sensor(
                2,
                SensorType(11, "wind speed", "m/s"),
                "device2", 1,
                "sensor2", favorite = false,
                public = true, mine = true,
                "Москва", 0.01,
                3.2, "m/s", 1686142800,
            ),
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
                    onClick = { filterShow = true },
                    label = { Text(text = stringResource(R.string.sensors_filter)) },
                )

                AssistChip(
                    onClick = { sortingShow = true },
                    label = { Text(text = stringResource(R.string.sensors_sorting)) },
                )

                FilterChip(
                    selected = filterMine,
                    onClick = { filterMine = !filterMine },
                    label = { Text(text = stringResource(R.string.sensors_mine)) },
                )
            }

            Divider()

            LazyColumn(
                modifier = Modifier.fillMaxHeight(),
            ) {
                items(sensors) { sensor ->
                    SensorItem(sensor)
                }
            }
        }
    }

    if (filterShow) {
        ModalBottomSheet(onDismissRequest = { filterShow = false }) {
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
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .fillMaxWidth(),
            ) {
                items(filterItems) {
                    FilterCheckbox(
                        checked = it.enabled.value,
                        stringRes = it.stringRes,
                    ) { it.enabled.value = !it.enabled.value }
                }
            }
        }
    }

    if (sortingShow) {
        ModalBottomSheet(onDismissRequest = { sortingShow = false }) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = stringResource(R.string.sensors_sort_title),
                    fontSize = 24.sp,
                    fontWeight = FontWeight(500),
                )
            }

            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .fillMaxWidth(),
            ) {
                items(sortingTypes) {
                    FilterRadioButton(
                        selected = (sortingType == it.sortingType),
                        onClick = { sortingType = it.sortingType },
                        stringRes = it.stringRes,
                    )
                }

                item {
                    FilterCheckbox(
                        checked = sortingDesc,
                        stringRes = R.string.sort_option_desc,
                    ) { sortingDesc = !sortingDesc }
                }

                item {
                    FilterCheckbox(
                        checked = sortingFav,
                        stringRes = R.string.sort_option_fav,
                    ) { sortingFav = !sortingFav }
                }

                item {
                    FilterCheckbox(
                        checked = sortingMine,
                        stringRes = R.string.sort_option_mine,
                    ) { sortingMine = !sortingMine }
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun SensorItem(sensor: Sensor) {
    ListItem(
        overlineContent = { Text(text = "${sensor.deviceName} от ${sensor.deviceOwner}") },
        headlineContent = { Text(text = sensor.type.name) },
        supportingContent = { Text(text = sensor.name) },
        trailingContent = {
            Column(
                horizontalAlignment = Alignment.End,
            ) {
                Text(text = "${sensor.distance} km")
                ElevatedCard(
                    shape = RectangleShape,
                ) {
                    Text(
                        text = "${sensor.value} ${sensor.unit}",
                        fontFamily = iosevkaFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(horizontal = 3.dp, vertical = 1.dp)
                    )
                }
            }
        }
    )
}

@ExperimentalMaterial3Api
@Composable
fun FilterCheckbox(checked: Boolean, stringRes: Int, onCheckedChange: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().clickable { onCheckedChange() }
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { onCheckedChange() },
        )
        Text(
            text = stringResource(id = stringRes),
        )
    }
}

@ExperimentalMaterial3Api
@Composable
fun FilterRadioButton(selected: Boolean, onClick: () -> Unit, stringRes: Int) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
        )
        Text(
            text = stringResource(id = stringRes),
            modifier = Modifier.clickable { onClick() },
        )
    }
}