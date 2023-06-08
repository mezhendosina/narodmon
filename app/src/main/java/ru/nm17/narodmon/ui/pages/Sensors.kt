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
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.nm17.narodmon.R
import ru.nm17.narodmon.db.entities.SensorType
import ru.nm17.narodmon.ui.bottomSheets.FilterSensorsBottomSheet
import ru.nm17.narodmon.ui.bottomSheets.SortSensorsBottomSheet
import ru.nm17.narodmon.ui.elements.GenericNavScaffold
import ru.nm17.narodmon.ui.elements.TileMap
import ru.nm17.narodmon.ui.entities.SensorEntity
import ru.nm17.narodmon.ui.entities.SensorFilterUiEntity
import ru.nm17.narodmon.ui.entities.SortingTypes
import ru.nm17.narodmon.ui.iosevkaFamily


@ExperimentalMaterial3Api
@Composable
fun SensorsPage(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    var searchActive by remember { mutableStateOf(false) }


    var sortingShow by remember { mutableStateOf(false) }
    var sortingType by remember { mutableStateOf(SortingTypes.DISTANCE) }

    var filterShow by remember { mutableStateOf(false) }
    var filterMine by remember { mutableStateOf(false) }

    val filterItems = remember {
        listOf(
            /* TODO:
             *  Заменить `code` на настоящее значение
             *  либо динамически его подгружать из ответа АПИ
             *  (см. /appInit, ключ в жсоне: types.type) */
            SensorFilterUiEntity(R.string.filter_temp, 0),
            SensorFilterUiEntity(R.string.filter_temp_water, 1),
            SensorFilterUiEntity(R.string.filter_temp_ground, 2),
            SensorFilterUiEntity(R.string.filter_temp_dew_point, 3),
            SensorFilterUiEntity(R.string.filter_humidity, 4),
            SensorFilterUiEntity(R.string.filter_pressure, 5),
            SensorFilterUiEntity(R.string.filter_lightness, 6),
            SensorFilterUiEntity(R.string.filter_uv, 7),
            SensorFilterUiEntity(R.string.filter_radiation, 8),
            SensorFilterUiEntity(R.string.filter_rainfall, 9),
            SensorFilterUiEntity(R.string.filter_dust, 10),
            SensorFilterUiEntity(R.string.filter_wind_speed, 11),
            SensorFilterUiEntity(R.string.filter_wind_direction, 12),
            SensorFilterUiEntity(R.string.filter_concentration, 13),
            SensorFilterUiEntity(R.string.filter_power, 14),
            SensorFilterUiEntity(R.string.filter_voltage, 15),
            SensorFilterUiEntity(R.string.filter_amperage, 16),
            SensorFilterUiEntity(R.string.filter_energy, 17),
            SensorFilterUiEntity(R.string.filter_battery, 18),
            SensorFilterUiEntity(R.string.filter_rxtx, 19),
            SensorFilterUiEntity(R.string.filter_signal, 20),
            SensorFilterUiEntity(R.string.filter_water_meter, 21),
            SensorFilterUiEntity(R.string.filter_time, 22),
        )
    }

    val sensorEntities = remember {
        mutableListOf(
            // TODO: загружать датчики с сервера. Этот список -- для макета
            SensorEntity(
                0,
                SensorType(0, "temp", "C"),
                "device0", 0,
                "sensor0", favorite = true,
                public = true, mine = false,
                "Москва", 0.4,
                20.0, "C", 1686142800,
            ),
            SensorEntity(
                1,
                SensorType(4, "humidity", "%"),
                "device1", 0,
                "sensor1", favorite = true,
                public = false, mine = false,
                "Подмосковье", 1.1,
                39.0, "%", 1686142800,
            ),
            SensorEntity(
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = if (!searchActive) 8.dp else 0.dp)
            ) {}

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(horizontal = 8.dp),
            ) {
                AssistChip(
                    onClick = { filterShow = true },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_filter),
                            contentDescription = stringResource(id = R.string.sensors_filter)
                        )
                    },
                    label = { Text(text = stringResource(R.string.sensors_filter)) },
                )

                AssistChip(
                    onClick = { sortingShow = true },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sort),
                            contentDescription = stringResource(id = R.string.sensors_sorting)
                        )
                    },
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
                items(sensorEntities) { sensor ->
                    SensorItem(sensor)
                }
            }
        }
    }

    if (filterShow) {
        FilterSensorsBottomSheet(
            filterItems,
            onDismissRequest = { filterShow = false }
        )
    }

    if (sortingShow) {
        SortSensorsBottomSheet(
            onApply = { s ->
                sortingType = s
                sortingShow = false
            },
            onDismissRequest = { sortingShow = false })
    }
}

@ExperimentalMaterial3Api
@Composable
fun SensorItem(sensorEntity: SensorEntity) {
    ListItem(
        overlineContent = { Text(text = "${sensorEntity.deviceName} от ${sensorEntity.deviceOwner}") },
        headlineContent = { Text(text = sensorEntity.type.name) },
        supportingContent = { Text(text = sensorEntity.name) },
        trailingContent = {
            Column(
                horizontalAlignment = Alignment.End,
            ) {
                Text(text = "${sensorEntity.distance} km")
                ElevatedCard(
                    shape = RectangleShape,
                ) {
                    Text(
                        text = "${sensorEntity.value} ${sensorEntity.unit}",
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
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCheckedChange() }
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
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().clickable { onClick.invoke() }
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
        )
        Text(text = stringResource(id = stringRes))
    }
}