package ru.nm17.narodmon.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
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
    var filter by remember { mutableStateOf(SensorsFilter.All) }

    val scrConfig = LocalConfiguration.current
    val mapHeight = scrConfig.screenHeightDp / 3

    GenericNavScaffold(
        title = { Text(text = stringResource(R.string.sensors_page_title)) }
    ) {
        Column(modifier = Modifier.padding(it)) {

            TileMap(modifier = Modifier.height(mapHeight.dp))

            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                SensorsFilterChip(
                    name = stringResource(R.string.sensors_filter_all),
                    checkFilter = { filter == SensorsFilter.All },
                    updateFilter = { filter = SensorsFilter.All },
                )

                SensorsFilterChip(
                    name = stringResource(R.string.sensors_filter_temp),
                    checkFilter = { filter == SensorsFilter.Thermometer },
                    updateFilter = { filter = SensorsFilter.Thermometer },
                )

                SensorsFilterChip(
                    name = stringResource(R.string.sensors_filter_camera),
                    checkFilter = { filter == SensorsFilter.Camera },
                    updateFilter = { filter = SensorsFilter.Camera },
                )
            }

            //Text(mapVM.state.scroll.toString())
            //Text(mapVM.state.scale.toString())
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SensorsFilterChip(
    name: String,
    checkFilter: () -> Boolean,
    updateFilter: () -> Unit,
) {
    FilterChip(
        selected = checkFilter(),
        onClick = updateFilter,
        label = { Text(name) },
    )
}