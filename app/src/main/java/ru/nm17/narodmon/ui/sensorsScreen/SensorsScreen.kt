package ru.nm17.narodmon.ui.sensorsScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.nm17.narodmon.R
import ru.nm17.narodmon.db.entities.SensorType
import ru.nm17.narodmon.ui.dialogs.FilterSensorsDialog
import ru.nm17.narodmon.ui.dialogs.SortSensorsDialog
import ru.nm17.narodmon.ui.elements.SensorItem
import ru.nm17.narodmon.ui.elements.TileMap
import ru.nm17.narodmon.ui.entities.SensorEntity
import ru.nm17.narodmon.ui.entities.SensorSortingUiEntity
import ru.nm17.narodmon.ui.entities.SortingTypes
import ru.nm17.narodmon.ui.navHost.MainScreenSealed
import ru.nm17.narodmon.ui.theme.NarodMonTheme
import ru.nm17.narodmon.ui.toChipTitle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SensorsScreen(outerNavController: NavController) {

    var searchQuery by remember { mutableStateOf("") }
    var searchActive by remember { mutableStateOf(false) }

    val scaffoldState = rememberBottomSheetScaffoldState()
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

    var filterShow by remember { mutableStateOf(false) }
    var filterMine by remember { mutableStateOf(false) }

    var sortingShow by remember { mutableStateOf(false) }
    var sortingType by remember {
        mutableStateOf(
            SensorSortingUiEntity(R.string.sort_by_distance, SortingTypes.DISTANCE)
        )
    }

    var sheetHeight by remember {
        mutableStateOf(SheetHeight.ExtraExpanded)
    }

    BottomSheetScaffold(modifier = Modifier.fillMaxSize(), sheetPeekHeight = when (sheetHeight) {
        SheetHeight.ExtraExpanded -> 256.dp
        SheetHeight.Expanded -> 128.dp
        SheetHeight.Hidden -> 0.dp
    }, scaffoldState = scaffoldState, sheetContent = {
        AnimatedVisibility(visible = scaffoldState.bottomSheetState.currentValue == SheetValue.Expanded) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text(stringResource(R.string.search)) },
                shape = SearchBarDefaults.inputFieldShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp)
            )
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            item {
                FilterChip(
                    selected = false,
                    onClick = { filterShow = true },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_filter),
                            contentDescription = stringResource(id = R.string.sensors_filter)
                        )
                    },
                    trailingIcon = {
//                            Icon(
//                                Icons.Filled.ArrowDropDown,
//                                "",
//                                tint = MaterialTheme.colorScheme.onBackground
//                            )
                    },

                    label = { Text(text = stringResource(R.string.sensors_filter)) },
                )
            }
            item {
                FilterChip(selected = sortingType.sortingType != SortingTypes.DISTANCE,
                    onClick = { sortingShow = true },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sort),
                            contentDescription = stringResource(id = R.string.sensors_sorting)
                        )
                    },
                    trailingIcon = {
//                            Icon(
//                                Icons.Filled.ArrowDropDown,
//                                "",
//                                tint = MaterialTheme.colorScheme.onBackground
//                            )
                    },
                    label = {
                        Text(
                            text = stringResource(
                                if (sortingType.sortingType == SortingTypes.DISTANCE) R.string.sensors_sorting
                                else sortingType.stringRes
                            ).toChipTitle(),
                        )
                    })
            }

            item {
                FilterChip(
                    selected = filterMine,
                    onClick = { filterMine = !filterMine },
                    leadingIcon = {
                        if (filterMine) {
                            Icon(
                                Icons.Rounded.Check, contentDescription = ""
                            )
                        }
                    },
                    label = { Text(text = stringResource(R.string.sensors_mine)) },
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxHeight(),
        ) {
            items(sensorEntities) { sensor ->
                SensorItem(sensor)
            }
        }
    }) {
        Box(modifier = Modifier.fillMaxSize()) {
            SearchBar(
                query = searchQuery,
                active = searchActive,
                onActiveChange = { active ->
                    searchActive = active
                    sheetHeight = if (active) SheetHeight.Hidden else SheetHeight.ExtraExpanded
                },
                onQueryChange = { query -> searchQuery = query },
                onSearch = { searchActive = false },
                placeholder = { Text(stringResource(R.string.search_sensors)) },
                trailingIcon = {
                    IconButton(onClick = { outerNavController.navigate(MainScreenSealed.Settings.route) }) {
                        Icon(
                            Icons.Outlined.Settings,
                            contentDescription = stringResource(R.string.settings)
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = if (!searchActive) 8.dp else 0.dp,
                    )
            ) {}

            TileMap(
                modifier = Modifier.fillMaxSize()
            ) {
                sheetHeight =
                    SheetHeight.Expanded // TODO придумать, чтобы менялось на SheetHeight.ExtraExpanded после взаимодействия с картой

            }
        }
    }
    if (sortingShow) {
        SortSensorsDialog(sortingType, onApply = {
            sortingType = it
            sortingShow = false
        }, onDismissRequest = {
            sortingShow = false
        })
    }
    if (filterShow) {
        FilterSensorsDialog(onApply = {
            // TODO применение фильтров
            filterShow = false
        }, onDismissRequest = { filterShow = false })
    }
}

@Preview
@Composable
fun PreviewNewSensors() {
    NarodMonTheme {
        SensorsScreen(rememberNavController())
    }
}
